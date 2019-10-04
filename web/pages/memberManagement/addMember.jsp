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

          function loadIsLifeMem(keyval) {
              //alert('keyval'+keyval); //1,0
              if(keyval==1){
                    $('#memExpdate').hide();
                    $('#memExpdate1').hide();
                    $('#memExpdate2').hide();
                    $('#memExpdate3').hide();
              }else{
                    $('#memExpdate').show();
                    $('#memExpdate1').show();
                    $('#memExpdate2').show();
                    $('#memExpdate3').show();
              }
              
             
            }
            function callIsMerried(keyval) {
                //alert('keyval'+keyval); //1-merrid,0-single
                if(keyval==1){
                    $('#familyId').show();
              }else{
                  $('#familyId').hide();
              }
                
            }
            
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
                                    <td class="formLable">Member ID<span class="mandatory">*</span></td> <td >:</td>
                                    <td><s:textfield id="memId" name="memId" readonly="true" cssClass="textField" /></td>                                    
                                    <td width="25px;"></td>
                                    
                                </tr> 
                                <tr>
                                    <td class="formLable">Member Name<span class="mandatory">*</span></td> <td >:</td>
                                    <td><s:textfield id="memName" name="memName" cssClass="textField" /></td>                                    
                                    <td width="25px;"></td>
                                    <td class="formLable">Member Nic<span class="mandatory">*</span></td> <td>:</td>
                                    <td><s:textfield id="memNic" name="memNic" cssClass="textField" /></td> 
                                    <td width="25px;"></td>
                                    <td class="formLable">Member Dob<span class="mandatory">*</span></td> <td>:</td>
                                    <td><sj:datepicker id="memDob" name="memDob" readonly="true" value="today"   changeYear="true"  changeMonth = "true" yearRange = "1950" buttonImageOnly="true" displayFormat="yy-mm-dd" cssClass="textField"  /></td>
                                </tr>    
                          
                                <tr>
                                    <td class="formLable">Land Phone Number<span class="mandatory">*</span></td> <td >:</td>
                                    <td><s:textfield id="phoneNo" name="phoneNo" cssClass="textField" /></td>                                    
                                    <td width="25px;"></td>
                                    <td class="formLable">Mobile Number<span class="mandatory">*</span></td> <td>:</td>
                                    <td><s:textfield id="mobileNo" name="mobileNo" cssClass="textField" /></td>  
                                    <td width="25px;"></td>
                                    <td class="formLable">Email<span class="mandatory">*</span></td> <td>:</td>
                                    <td><s:textfield id="email" name="email" cssClass="textField" /></td> 
                                </tr> 
                                
                                <tr>
                                    <td class="formLable">Qualification<span class="mandatory">*</span></td> <td >:</td>
                                    <td><s:textfield id="qualification" name="qualification" cssClass="textField" /></td>                                    
                                    <td width="25px;"></td>
                                    <td class="formLable">Permanent Address<span class="mandatory">*</span></td> <td>:</td>
                                    <td><s:textfield id="perAddress" name="perAddress" cssClass="textField" /></td>  
                                    <td width="25px;"></td>
                                    <td class="formLable">Temporary Address<span class="mandatory">*</span></td> <td>:</td>
                                    <td><s:textfield id="temAddress" name="temAddress" cssClass="textField" /></td> 
                                </tr> 
                                
                                <tr>
                                    <td class="formLable">Born Place<span class="mandatory">*</span></td> <td >:</td>
                                    <td><s:textfield id="memBornPlace" name="memBornPlace" cssClass="textField" /></td>                                    
                                    <td width="25px;"></td>
                                    <td class="formLable">Member Cast<span class="mandatory">*</span></td>  <td >:</td>
                                    <td><s:select  name="memCast" id="memCast"  headerKey="-1" 
                                               headerValue="---Select---"  list="%{memCastList}"  cssClass="dropdown" /></td> 
                                    <td width="25px;"></td>
                                    <td class="formLable">Member Sub Cast<span class="mandatory">*</span></td> <td >:</td>
                                    <td><s:textfield id="memSubCast" name="memSubCast" cssClass="textField" /></td> 
                                </tr>
                                
                                <tr> 
                                    <td class="formLable">Membership Type<span class="mandatory">*</span></td>  <td >:</td>
                                    <td><s:select  name="memIslife" id="memIslife" list="%{memIslifeList}" 
                                               listKey="key" listValue="value"  onchange="loadIsLifeMem(this.value)"  headerKey="-1"    headerValue="---Select---"     cssClass="dropdown" /></td> 
                                    <td width="25px;"></td>
                                    <td class="formLable" id="memExpdate1" >Exp Date<span class="mandatory">*</span></td> <td id="memExpdate2">:</td>
                                    <td id="memExpdate3"><sj:datepicker id="memExpdate" name="memExpdate" readonly="true" value="today"  minDate="today" changeYear="true" buttonImageOnly="true" displayFormat="yy-mm-dd" cssClass="textField"  /></td>
                                </tr>
                                <tr> 
                                    <td class="formLable">Number Of Brothers<span class="mandatory">*</span></td>  <td >:</td>
                                    <td><s:select  name="noOfBrother" id="noOfBrother"  list="%{numberList}"  cssClass="dropdown" /></td>
                                    <td width="25px;"></td>
                                    <td class="formLable">Number Of Sisters<span class="mandatory">*</span></td> <td >:</td>
                                    <td><s:select  name="noOfSister" id="noOfSister"  list="%{numberList}"  cssClass="dropdown" /></td>
                                </tr>
                                <tr>
                                    <td class="formLable">Occupation<span class="mandatory">*</span></td> <td >:</td>
                                    <td><s:textfield id="jobTitle" name="jobTitle" cssClass="textField" /></td>                                    
                                    <td width="25px;"></td>
                                    <td class="formLable">Office Address<span class="mandatory">*</span></td> <td>:</td>
                                    <td><s:textfield id="jobAddress" name="jobAddress" cssClass="textField" /></td>  
                                    <td width="25px;"></td>
                                    <td class="formLable">Office Phone<span class="mandatory">*</span></td> <td>:</td>
                                    <td><s:textfield id="jobPhone" name="jobPhone" cssClass="textField" /></td> 
                                </tr>
                                </table>
                                
                                <fieldset style="background-color:rgb(245,249,249)">
                                    <legend>Parent Details</legend>
                                    <table>
                                    <tr>
                                        <td class="formLable">Father Name<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:textfield id="fatName" name="fatName" cssClass="textField" /></td>                                    
                                        <td width="25px;"></td>
                                        <td class="formLable">Father Birth Place<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield id="fatBirthPlace" name="fatBirthPlace" cssClass="textField" /></td>  
                                        <td width="25px;"></td>
                                        <td class="formLable">Father Cast<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:select  name="fatCast" id="fatCast"  headerKey="-1" 
                                               headerValue="---Select---"  list="%{memCastList}"  cssClass="dropdown" /></td> 
                                    </tr>
                                    <tr>
                                        <td class="formLable">Mother Name<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:textfield id="mothName" name="mothName" cssClass="textField" /></td>                                    
                                        <td width="25px;"></td>
                                        <td class="formLable">Mother Birth Place<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield id="mothBirthPlace" name="mothBirthPlace" cssClass="textField" /></td>  
                                        <td width="25px;"></td>
                                        <td class="formLable">Mother Cast<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:select  name="mothCast" id="mothCast"  headerKey="-1" 
                                               headerValue="---Select---"  list="%{memCastList}"  cssClass="dropdown" /></td> 
                                    </tr>
                                    
                                    <tr>
                                        <td class="formLable">Grandfather Name<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:textfield id="grandFatName" name="grandFatName" cssClass="textField" /></td>                                    
                                        <td width="25px;"></td>
                                        <td class="formLable">Grandfather Birth Place<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield id="grandFatBirthPlace" name="grandFatBirthPlace" cssClass="textField" /></td>  
                                        <td width="25px;"></td>
                                        <td class="formLable">Grandfather Cast<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:select  name="grandFatCast" id="grandFatCast"  headerKey="-1" 
                                               headerValue="---Select---"  list="%{memCastList}"  cssClass="dropdown" /></td> 
                                    </tr>
                                    <tr>
                                        <td class="formLable">Grandmother Name<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:textfield id="grandMothName" name="grandMothName" cssClass="textField" /></td>                                    
                                        <td width="25px;"></td>
                                        <td class="formLable">Grandmother Birth Place<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield id="grandMothBirthPlace" name="grandMothBirthPlace" cssClass="textField" /></td>  
                                        <td width="25px;"></td>
                                        <td class="formLable">Grandmother Cast<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:select  name="grandMothCast" id="grandMothCast"  headerKey="-1" 
                                               headerValue="---Select---"  list="%{memCastList}"  cssClass="dropdown" /></td> 
                                    </tr>
                                    
                                    
                                    </table>
                                </fieldset>
                                
                            <table>  
                                <tr>
                                        <td class="formLable">Meride Status<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:select  name="isMerrid" id="isMerrid" list="%{isMerridList}" 
                                               listKey="key" listValue="value"  onchange="callIsMerried(this.value)"  headerKey="-1"    headerValue="---Select---"     cssClass="dropdown" /></td> 
                                </tr>
                            </table>
                                <fieldset id="familyId" style="background-color:rgb(245,249,249)">
                                    <legend>Family Details</legend>
                                    <table>
                                    <tr>
                                        <td class="formLable">Spouse Name<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:textfield id="wifeName" name="wifeName" cssClass="textField" /></td>                                    
                                        <td width="25px;"></td>
                                        <td class="formLable">Num of Sun<span class="mandatory">*</span></td> <td>:</td> 
                                        <td><s:select  name="noOfSuns" id="noOfSuns"  list="%{numberList}"  cssClass="dropdown" headerKey="0"    headerValue="-0-" /></td>
                                        <td width="25px;"></td>
                                        <td class="formLable">Num of Daughter<span class="mandatory">*</span></td> <td>:</td> 
                                        <td><s:select  name="noOfDoters" id="noOfDoters"  list="%{numberList}"  cssClass="dropdown" headerKey="0"    headerValue="-0-"/></td>
                                    </tr>
                                    <tr>
                                        <td class="formLable">DOB<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:textfield id="wifeName" name="wifeName" cssClass="textField" /></td>                                    
                                        <td width="25px;"></td>
                                        <td class="formLable">Address<span class="mandatory">*</span></td> <td>:</td> 
                                        <td><s:textfield id="wifeName" name="wifeName" cssClass="textField" /></td> 
                                        <td width="25px;"></td>
                                        <td class="formLable">Phone<span class="mandatory">*</span></td> <td>:</td> 
                                        <td><s:textfield id="wifeName" name="wifeName" cssClass="textField" /></td> 
                                    </tr>
									<tr>
                                        <td class="formLable">Mobile<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:textfield id="wifeName" name="wifeName" cssClass="textField" /></td>                                    
                                        <td width="25px;"></td>
                                        <td class="formLable">Email<span class="mandatory">*</span></td> <td>:</td> 
                                        <td><s:textfield id="wifeName" name="wifeName" cssClass="textField" /></td> 
                                        <td width="25px;"></td>

                                    </tr>
                                    <tr>
                                        <td class="formLable">Wife Father Name<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:textfield id="wfatName" name="wfatName" cssClass="textField" /></td>                                    
                                        <td width="25px;"></td>
                                        <td class="formLable">Wife Father Birth Place<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield id="wfatBirthPlace" name="wfatBirthPlace" cssClass="textField" /></td>  
                                        <td width="25px;"></td>
                                        <td class="formLable">Wife Father Cast<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:select  name="wfatCast" id="wfatCast"  headerKey="-1" 
                                               headerValue="---Select---"  list="%{memCastList}"  cssClass="dropdown" /></td> 
                                    </tr>
                                    <tr>
                                        <td class="formLable">Wife Mother Name<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:textfield id="wmothName" name="wmothName" cssClass="textField" /></td>                                    
                                        <td width="25px;"></td>
                                        <td class="formLable">Wife Mother Birth Place<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield id="wmothBirthPlace" name="wmothBirthPlace" cssClass="textField" /></td>  
                                        <td width="25px;"></td>
                                        <td class="formLable">Wife Mother Cast<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:select  name="wmothCast" id="wmothCast"  headerKey="-1" 
                                               headerValue="---Select---"  list="%{memCastList}"  cssClass="dropdown" /></td> 
                                    </tr>
                                    
                                    <tr>
                                        <td class="formLable">Wife Grandfather Name<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:textfield id="wgrandFatName" name="wgrandFatName" cssClass="textField" /></td>                                    
                                        <td width="25px;"></td>
                                        <td class="formLable">Wife Grandfather Birth Place<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield id="wgrandFatBirthPlace" name="wgrandFatBirthPlace" cssClass="textField" /></td>  
                                        <td width="25px;"></td>
                                        <td class="formLable">Wife Grandfather Cast<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:select  name="wgrandFatCast" id="wgrandFatCast"  headerKey="-1" 
                                               headerValue="---Select---"  list="%{memCastList}"  cssClass="dropdown" /></td> 
                                    </tr>
                                    <tr>
                                        <td class="formLable">Wife Grandmother Name<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:textfield id="wgrandMothName" name="wgrandMothName" cssClass="textField" /></td>                                    
                                        <td width="25px;"></td>
                                        <td class="formLable">Wife Grandmother Birth Place<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield id="wgrandMothBirthPlace" name="wgrandMothBirthPlace" cssClass="textField" /></td>  
                                        <td width="25px;"></td>
                                        <td class="formLable">Wife Grandmother Cast<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:select  name="wgrandMothCast" id="wgrandMothCast"  headerKey="-1" 
                                               headerValue="---Select---"  list="%{memCastList}"  cssClass="dropdown" /></td> 
                                    </tr>
                                    </table>
                                </fieldset>
								
							<fieldset id="familyId" style="background-color:rgb(245,249,249)">
                                    <legend>Upload Images</legend>
                                    <table>
                                    <tr>
                                        <td class="formLable">Member Photo<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:file  id = "upfile" name="upfile" label="File" cssClass="fileField"  /></td>                                    
                                        <td width="25px;"></td>
                                    </tr>
									<tr>
                                        <td class="formLable">Member Femaly Photo<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:file  id = "upfile" name="upfile" label="File" cssClass="fileField"  /></td>                                    
                                        <td width="25px;"></td>
                                    </tr>

                                    </table>
                             </fieldset>	
                            <table>
                                <tr>
                                    <td class="content_td formLable" colspan="7"><span class="mandatory_text">Mandatory fields are marked with</span><span class="mandatory">*</span></td>
                                </tr>
                            </table>
                            <table class="form_table">
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

			<s:form id="addFile" action="UploadFilebulkMsgMng-remove"  theme="simple" enctype="multipart/form-data"  method="post" >         
                            <table class="form_table" style="margin-top: 5px;" border="0px"> 
                                <tr style=" height: 19px;">
                                    <s:textfield id="memId" name="memId" readonly="true" cssClass="textField" hidden="true" />
                                </tr>
                                <tr>
                                    <td class="content_td formLable" width="100px;">Upload File k</td> 
                                    <td class="content_td formLable" width="5px">:</td>
                                    <td colspan="2" width="300px"><s:file  id = "upfile" name="upfile" label="File" cssClass="fileField"  /></td>
                                    <td style="height: 200px; width:200px"><img src="/imagesK/members/MEM_M00007.png"/></td>
                                    <!--C:\Users\Kreshan Rajendran\AppData\Roaming\NetBeans\8.2\config\GF_4.1.1\domain1\docroot-->
                                </tr>
                                <tr style="height: 73px;">
                                    
                                </tr>

                                <tr>
                                    <td align="left" colspan="4">
                                         <s:url var="bulkAddurlfile" action="UploadFileaddMember"/>                                   
                                        <sj:submit   button="true" href="%{bulkAddurlfile}" value="Add"   targets="divmsg"  cssClass="button_sadd" disabled="#vadd"/>
                                        
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

