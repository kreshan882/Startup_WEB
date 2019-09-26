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
                return "<a href='#' onClick='deleteInit(&#34;" + rowObject.memOriId + "&#34;,&#34;" + rowObject.memId + "&#34;)'><img src='${pageContext.request.contextPath}/resources/images/iconDelete.png'  /></a>";
            }

            function editformatter(cellvalue, options, rowObject) {
                return "<a href='#' onClick='javascript:editNow(&#34;" + cellvalue + "&#34;)'><img src ='${pageContext.request.contextPath}/resources/images/iconEdit.png' /></a>";
            }
            
            function downloadformatter(cellvalue, options, rowObject) {
                return "<a href='#' onClick='javascript:downloadNow(&#34;" + cellvalue + "&#34;)'><img src ='${pageContext.request.contextPath}/resources/images/iconEdit.png' /></a>";
            }
//            function pdchangeformatter(cellvalue, options, rowObject) {
//                return "<a href='#' onClick='javascript:pdchangeNow(&#34;" + cellvalue + "&#34;)'><img src ='${pageContext.request.contextPath}/resources/images/iconEdit.png' /></a>";
//            }

            function deleteInit(keyval,keyvaldes) {
                $("#confirmdialogbox").data('keyval', keyval).dialog('open');
                $("#confirmdialogbox").html('<br><b><font size="3" color="red"><center>Please confirm to delete member id : ' + keyvaldes + '');
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
                    url: '${pageContext.request.contextPath}/DeleteeditViewMember',
                    data: {memId: keyval},
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
                    url: '${pageContext.request.contextPath}/FindeditViewMember',
                    data: {memId: keyval},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {

                        $('#editForm').show();
                        $('#searchForm').hide();

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
            
            function downloadNow(keyval) {
                $('#divmsg').empty();
                $.ajax({
                    url: '${pageContext.request.contextPath}/DownloadeditViewMember',
                    data: {memId: keyval},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {

                        $('#editForm').show();
                        $('#searchForm').hide();

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

            $.subscribe('grideReload', function (event, data) {
                $('#searchname').val("");
                $('#divmsg').empty();
               // window.location = "${pageContext.request.contextPath}/editViewMember.action"
            });
        </script>
    </head>

    <body style="overflow:hidden" onload="load()">
        <div class="wrapper">

            <div class="body_content" id="includedContent" >

                <div class="watermark"></div>
                <div class="heading">Member details</div>
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

                        <div class="contentcenter">
                    <s:form id="searchForm" action="usrMng" method="post" action="DownloadeditViewMember" theme="simple">         
                            <table class="form_table">              
                                <tr>
                                    <td class="content_td formLable">User Name</td>
                                    <td>:</td>
                                    <td colspan="2"><s:textfield name="searchname"  id="searchname" cssClass="textField" /> </td>
                                    <td class="content_td formLable">

                                    </td>
                                </tr>
                            </table><table class="form_table">
                                </br>
                                <tr>                                
                                    <td> 

                                        <sj:a id="searchid"  button="true"    onClickTopics="onclicksearch"  cssClass="button_asave"  role="button" aria-disabled="false" >Search</sj:a>
                                        <sj:a id="refreshbut"  button="true"  onClickTopics="grideReload"  cssClass="button_asave">Refresh</sj:a>  
                                        <sj:submit button="true" cssStyle="font-size:12px;padding: 5px 5px;height:27; width:106px;" cssClass="button_asave" value="Print PDF" />  
                                    </td>
                                </tr>
                            </table>
                    </s:form>

                        <s:form id="editForm"  theme="simple" method="post" cssStyle="display:none" >
                            <table class="form_table">

                                <tr> 
<%--                                    <td style="display: none;"> <s:textfield name="memId" id="memId" cssClass="textField" /></td>--%>
                                    <td class="formLable">Member ID<span class="mandatory">*</span></td> <td >:</td>
                                    <td><s:textfield id="memId" name="memId" readonly="true" cssClass="textField" /></td>                                    

                                    <td width="25px"></td>
                                    <td class="formLable">Name<span class="mandatory">*</span></td> <td>:</td>
                                    <td ><s:textfield name="memName" id="memName" cssClass="textField" /></td>
                                </tr>

                                <tr>
                                    <td class="formLable">Email<span class="mandatory">*</span></td> <td>:</td>
                                    <td ><s:textfield name="email" id="email" cssClass="textField" /></td>
                                    <td width="25px"></td>
                                    <td class="formLable">Mobile</td> <td>:</td>
                                    <td ><s:textfield name="phoneNo" id="phoneNo" cssClass="textField" placeholder="+94777123456"/></td>
                                </tr>
                                <tr> 
                                    <td class="formLable">Status<span class="mandatory">*</span></td><td>:</td>
                                    <td ><s:select  name="status" id="status" list="%{statusList}" 
                                               listKey="key" listValue="value"    headerKey="-1"    headerValue="---Select---"     cssClass="dropdown" />
                                    </td>
                                    <td width="25px"></td>
                                    <td class="formLable">Cast<span class="mandatory">*</span></td> <td>:</td>
                                    <td ><s:select  name="memCast" id="memCast" list="%{memCastList}" 
                                               listKey="key" listValue="value"    headerKey="-1"    headerValue="---Select---"     cssClass="dropdown" />
                                    </td>
                                <tr>
                                    <td class="content_td formLable" colspan="7"><span class="mandatory_text">Mandatory fields are marked with</span><span class="mandatory">*</span></td>
                                </tr>
                            </table><table class="form_table">
                                </br>
                                <tr>                                
                                    <td> <s:url var="updateuserurl" action="UpdateusrMng"/>                                   
                                        <sj:submit   button="true" href="%{updateuserurl}" value="Update"   targets="divmsg"  cssClass="button_sadd" disabled="#vupdate"/>
                                        <sj:submit button="true" value="Reset" onClick="resetUpdateForm()" cssClass="button_aback"/>
                                        <sj:a href="#" name="back" button="true" onclick="backToMain()"  cssClass="button_aback" >Back</sj:a>    
                                        </td>
                                    </tr>
                                </table>
                        </s:form>  
                    </div>


                    <div class="viewuser_tbl">
                        <div id="tablediv">

                            <sj:dialog 
                                id="confirmdialogbox" 
                                buttons="{ 
                                'OK':function() { deleteNow($(this).data('keyval'));$( this ).dialog( 'close' ); },
                                'Cancel':function() { $( this ).dialog( 'close' );} 
                                }" 
                                autoOpen="false" 
                                modal="true" 
                                title="Confirm message"
                                width="400"
                                height="150"
                                position="center"
                                />

                            <sj:dialog 
                                id="dialogbox" 
                                buttons="{
                                'OK':function() { $( this ).dialog( 'close' );}
                                }"  
                                autoOpen="false" 
                                modal="true" 
                                title="Delete messae" 
                                width="400"
                                height="150"
                                position="center"
                                />
                            <!-- End delete dialog box -->

                            <s:url var="listurl" action="ListeditViewMember"/>
                            <sjg:grid
                                id="gridtable"
                                caption="User Management"
                                dataType="json"
                                href="%{listurl}"
                                pager="true"
                                gridModel="gridModel"
                                rowList="10,15,20"
                                rowNum="10"
                                autowidth="true"
                                shrinkToFit = "false"
                                rownumbers="true"
                                onCompleteTopics="completetopics"
                                rowTotal="false"
                                viewrecords="true"
                                >
                                
                                <sjg:gridColumn name="memOriId" index="MEM_ID" title="ProfileId"  frozen="false" hidden="true"/>
                                
                                <sjg:gridColumn name="memId" index="MEM_ID" title="Member ID" align="left" width="150" frozen="false" sortable="true"/>
                                <sjg:gridColumn name="memName" index="MEM_NAME" title="Name" align="left" width="150" sortable="true"/>                    
                                <sjg:gridColumn name="memNic" index="MEM_NIC" title="Nic" align="left"  width="150"  sortable="true"/>
                                <sjg:gridColumn name="memDob" index="MEM_DOB" title="Dob" align="left"  width="100"  sortable="true"/>
                                <sjg:gridColumn name="phoneNo" index="MEM_PHONE" title="Phone" align="left" width="100" sortable="true"/>
                                <sjg:gridColumn name="memBornPlace" index="MEM_BORN_PLACE" title="Born Place" align="left" width="100" sortable="true"/>
                                <sjg:gridColumn name="memCast" index="CAST_NAME" title="Cast" align="left" width="100" sortable="true"/>
                                
                                <sjg:gridColumn name="regDate" index="CREATE_DATE" title="Reg Date" align="center"  width="100"  sortable="true"/>
                                
                                <sjg:gridColumn name="status" index="STATUS" title="Status" align="center" width="80" formatter="statusformatter" sortable="true"/>  
                       
                                <%--<sjg:gridColumn name="username" index="USERNAME" title="Reset Pw" align="center" width="7" align="center"  formatter="pdchangeformatter" sortable="false" hidden="#vresetpass"/>--%>
                                <sjg:gridColumn name="memOriId" index="MEM_ID" title="Edit" align="center" width="80" align="center"  formatter="editformatter" sortable="false" hidden="#vupdate"/>
                                <sjg:gridColumn name="memOriId" index="MEM_ID" title="Delete" align="center" width="80" align="center"   formatter="deleteformatter" sortable="false" hidden="#vdelete"/>
                                <sjg:gridColumn name="memOriId" index="MEM_ID" title="Download" align="center" width="80" align="center"   formatter="downloadformatter" sortable="false" hidden="#vupdate"/>

                            </sjg:grid> 

                        </div> 



                    </div>
                </div>              

            </div>
        </div>
        <jsp:include page="../../footer.jsp" /> 

    </body>
</html>
