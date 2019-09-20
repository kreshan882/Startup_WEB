<%-- 
    Document   : addMember
    Created on : Sep 19, 2019, 10:36:59 AM
    Author     : Kreshan Rajendran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"  %>  
<%@taglib  uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>      

<html>
    <head>
        <jsp:include page="/Styles.jsp" />
        <jsp:include page="../../header.jsp" />            

        <script type="text/javascript">

            function load() {
                if ('true' === $('#vadd').val()) {
                    $('#addid').hide();
                }
            }

            function resetData() {
                $('#name').val("");
                $('#username').val("");
                $('#password').val("");
                $('#repassword').val("");
                $('#email').val("");
                $('#mobile').val("");
                $('#userPro').val("-1");

                $('#upname').val("");
                $('#upusername').val("");
                $('#upstatus').val("-1");
                $('#upuserPro').val("-1");
                $('#upemail').val("");
                $('#upmobile').val("");

                jQuery("#gridtable").trigger("reloadGrid");
            }

            function statusformatter(cellvalue, options, rowObject) {
                if (cellvalue == '1') {
                    var html = "<img src='${pageContext.request.contextPath}/resources/images/iconActive.png' />";
                } else {
                    var html = "<img src= '${pageContext.request.contextPath}/resources/images/iconInactive.png' />";
                }
                return html;
            }


            function deleteformatter(cellvalue, options, rowObject) {
                return "<a href='#' onClick='deleteInit(&#34;" + cellvalue + "&#34;)'><img src='${pageContext.request.contextPath}/resources/images/iconDelete.png'  /></a>";
            }

            function editformatter(cellvalue, options, rowObject) {
                return "<a href='#' onClick='javascript:editNow(&#34;" + cellvalue + "&#34;)'><img src ='${pageContext.request.contextPath}/resources/images/iconEdit.png' /></a>";
            }
//            function pdchangeformatter(cellvalue, options, rowObject) {
//                return "<a href='#' onClick='javascript:pdchangeNow(&#34;" + cellvalue + "&#34;)'><img src ='${pageContext.request.contextPath}/resources/images/iconEdit.png' /></a>";
//            }

            function deleteInit(keyval) {
                $("#confirmdialogbox").data('keyval', keyval).dialog('open');
                $("#confirmdialogbox").html('<br><b><font size="3" color="red"><center>Please confirm to delete user : ' + keyval + '');
                return false;
            }

//             function pdchangeNow(keyval){
//                $('#message').empty();
//
//                $('#pwresetForm').show();
//                $('#editForm').hide();
//                $('#searchForm').hide();
//                
//                $('#rusername').val(keyval);
//            }

            function deleteNow(keyval) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/DeleteusrMng',
                    data: {username: keyval},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        if (data.success) {
                            resetData();
                            backToMain();
                            $("#dialogbox").dialog('open');
                            $("#dialogbox").html('<br><b><font size="3" color="green"><center>' + data.message + ' ');
                        } else {
                            $("#dialogbox").dialog('open');
                            $("#dialogbox").html('<br><b><font size="3" color="red"><center>' + data.message + ' ');
                        }
                        jQuery("#gridtable").trigger("reloadGrid");
                    },
                    error: function (data) {
                        window.location = "${pageContext.request.contextPath}/LogoutloginCall.blb?";
                    }
                });

            }


            function editNow(keyval) {
                $('#divmsg').empty();
                $.ajax({
                    url: '${pageContext.request.contextPath}/FindusrMng',
                    data: {username: keyval},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {

                        $('#editForm').show();
                        $('#pwresetForm').hide();
                        $('#searchForm').hide();
                        $('#addForm').hide();

                        $('#upusernamecopy').val(data.upusernamecopy);
                        $('#upusername').val(data.upusername);
                        $('#upusername').attr('readOnly', true).val();
                        
                        $('#upname').val(data.upname);
                        $('#upuserPro').val(data.upuserPro);
                        $('#upstatus').val(data.upstatus);
                        $('#upemail').val(data.upemail);
                        $('#upaddress').val(data.upaddress);
                        $('#upmobile').val(data.upmobile);
                        $('#upnic').val(data.upnic);

                    },
                    error: function (data) {
                        window.location = "${pageContext.request.contextPath}/LogoutloginCall.blb?";
                    }
                });
            }


            function backToMain() {
                $('#editForm').hide();
                $('#pwresetForm').hide();
                $('#searchForm').show();
                $('#addForm').hide();

                $('#divmsg').empty();
                jQuery("#gridtable").trigger("reloadGrid");

            }




            $.subscribe('onclicksearch', function (event, data) {
                var searchname = $('#searchname').val();
                $("#gridtable").jqGrid('setGridParam', {postData: {searchname: searchname, search: true}});
                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");
            });

            $.subscribe('loadAddForm', function (event, data) {
                $('#editForm').hide();
                $('#pwresetForm').hide();
                $('#searchForm').hide();
                $('#addForm').show();
            });

            //reset Datas
            function ResetSearchForm() {
                $('#searchname').val("");
                $('#divmsg').empty();
                jQuery("#gridtable").trigger("reloadGrid");
            }
            function ResetAddForm() {
                resetData();
                $('#divmsg').empty();
            }

            function resetUpdateForm() {
                var upusername = $('#upusername').val();
                editNow(upusername);
                $('#divmsg').empty();
                jQuery("#gridtable").trigger("reloadGrid");

            }

//            function loadAuthType(keyval){ 
//                if(keyval=='1'){
//                    $('#imei').hide();
//                    $('#password').show();
//                    $('#repassword').show();
//                }else{ //2
//                    $('#imei').show()
//                    $('#password').hide();
//                    $('#repassword').hide();
//                }
//                
//            }
        </script>
    </head>

    <body style="overflow:hidden" onload="load()">
        <div class="wrapper">

            <div class="body_content" id="includedContent" >

                <div class="watermark"></div>
                <div class="heading">Register Member</div>
                <div class="AddUser_box ">

                    <div class="message">         
                        <s:div id="divmsg">
                            <i style="color: red">  <s:actionerror theme="jquery"/></i>
                            <i style="color: green"> <s:actionmessage theme="jquery"/></i>
                        </s:div>         
                    </div>
                    <s:hidden  id="vadd" name="vadd" default="true"/>
                    <s:set id="vadd" var="vadd"><s:property  value="vadd" default="true"/></s:set>
                    <s:set var="vupdate"><s:property value="vupdate" default="true"/></s:set>
                    <s:set var="vdelete"><s:property value="vdelete" default="true"/></s:set>
                    <s:set var="vdownload"><s:property value="vdownload" default="true"/></s:set>
                    <s:set var="vresetpass"><s:property value="vresetpass" default="true"/></s:set>

                        <div class="contentcenter">


                        <s:form  id="addForm"  theme="simple" method="post" >
                            <table class="form_table">
                                <tr>
                                    <td class="formLable">Member Name<span class="mandatory">*</span></td> <td >:</td>
                                    <td><s:textfield id="memName" name="memName" cssClass="textField" /></td>                                    
                                    <td width="25px;"></td>
                                    <td class="formLable">Member dob<span class="mandatory">*</span></td> <td>:</td>
                                    <td><sj:datepicker id="memDob" name="memDob" readonly="true" value="today"   changeYear="true" buttonImageOnly="true" displayFormat="yy-mm-dd" cssClass="textField"  /></td>
                                    <td width="25px;"></td>
                                    <td class="formLable">Member dob<span class="mandatory">*</span></td> <td>:</td>
                                    <td><s:textfield id="memDob" name="memDob" cssClass="textField" /></td>
                                </tr>    
                          
                                
                                <tr>
                                    <td class="formLable">Member cast<span class="mandatory">*</span></td>  <td >:</td>
                                    <td><s:select  name="memCast" id="memCast"  headerKey="-1" 
                                               headerValue="---Select---"  list="%{memCastList}"  cssClass="dropdown" /></td>
                                     <td width="25px;"></td>
                                     
                                     <td class="formLable">Is life member<span class="mandatory">*</span></td>  <td >:</td>
                                   <td><s:select  name="memIslife" id="memIslife"  headerKey="-1" 
                                               headerValue="---Select---"  list="%{memIslifeList}"  cssClass="dropdown" /></td>
                                </tr>
                                
                                
                                <tr>
                                    <td class="content_td formLable" colspan="7"><span class="mandatory_text">Mandatory fields are marked with</span><span class="mandatory">*</span></td>
                                </tr>
                            </table><table class="form_table">
                                </br>
                                <tr>                                
                                    <td> <s:url var="addurl" action="AddaddMember"/>                                   
                                        <sj:submit   button="true" href="%{addurl}" value="Add"   targets="divmsg"  cssClass="button_sadd" disabled="#vadd"/> 
                                        <sj:submit id="resetida" button="true" value="Reset" onclick="ResetAddForm()"   cssClass="button_aback" disabled="false" />
                                        <sj:submit id="backida" button="true" value="Back" onclick="backToMain()"   cssClass="button_aback" disabled="false" /> 
                                    </td>
                                </tr>
                            </table>
                        </s:form>



  
                    </div>



                </div>              

            </div>
        </div>
        <jsp:include page="../../footer.jsp" /> 

    </body>
</html>

