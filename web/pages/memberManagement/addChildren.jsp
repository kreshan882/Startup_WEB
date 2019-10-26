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
                
                $('#childName').val("");
                $('#childDob').val("");
                $('#childGender').val("-1");
                $('#childMerStatus').val("-1");
                $('#upuserPro').val("-1");
                $('#childEdu').val("");
                $('#childAddr').val("");
                $('#childPhone').val("");
                $('#childMobile').val("");
                $('#childEmail').val("");

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


            function deleteInit(keyval) {
                $("#confirmdialogbox").data('keyval', keyval).dialog('open');
                $("#confirmdialogbox").html('<br><b><font size="3" color="red"><center>Please confirm to delete Childrean id : ' + keyval + '');
                return false;
            }



            function deleteNow(keyval) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/DeletechildMng',
                    data: {childId: keyval},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        if (data.success) {
                            //resetData();
                            //backToMain();
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
                //alert("cid:"+keyval)
                $('#divmsg').empty();
                $.ajax({
                    url: '${pageContext.request.contextPath}/FindchildMng',
                    data: {childId: keyval},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {

                        $('#editForm').show();
                        $('#addForm').hide();

                        $('#childId').val(data.childId);
                        $('#upmemberload').val(data.memberload);
                        $('#upmemberload').attr('readOnly', true).val();
                        
                        $('#upchildName').val(data.childName);
                        $('#upchildDob').val(data.childDob);
                        $('#upchildGender').val(data.childGender);
                        $('#upchildMerStatus').val(data.childMerStatus);
                        
                        $('#upchildEdu').val(data.childEdu);
                        $('#upchildAddr').val(data.childAddr);
                        $('#upchildPhone').val(data.childPhone);
                        $('#upchildMobile').val(data.childMobile);
                        $('#upchildEmail').val(data.childEmail);

                    },
                    error: function (data) {
                        window.location = "${pageContext.request.contextPath}/LogoutloginCall.blb?";
                    }
                });
            }


//            function backToMain() {
//                $('#editForm').hide();
//                $('#addForm').hide();
//
//                $('#divmsg').empty();
//                jQuery("#gridtable").trigger("reloadGrid");
//
//            }






            $.subscribe('loadAddForm', function (event, data) {
                $('#editForm').hide();
                $('#addForm').show();
            });


            function ResetAddForm() {
                resetData();
                $('#divmsg').empty();
                
                $('#memberload').val("-1");
                var memload=$('#memberload')
                $("#gridtable").jqGrid('setGridParam', {postData: {memberload: memload, search: true}});
                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");

            }

            function resetUpdateForm() {
                var childId = $('#childId').val();
                editNow(childId);
                $('#divmsg').empty();
                jQuery("#gridtable").trigger("reloadGrid");

            }



            function loadMemChildreans(keyval) {
                //alert("mc:"+keyval)
                $('#divmsg').empty();
                
                $("#gridtable").jqGrid('setGridParam', {postData: {memberload: keyval, search: true}});
                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");

            }

        </script>
    </head>

    <body style="overflow:hidden" onload="load()">
        <div class="wrapper">

            <div class="body_content" id="includedContent" >

                <div class="watermark"></div>
                <div class="heading">Children Management</div>
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
    

                        <s:form  id="addForm"  theme="simple" method="post"  >
                            <table class="form_table">
                                <tr>
                                    <td class="formLable">Select Member-<span class="mandatory">*</span></td>                                     <td>:</td>
                                    <td><s:select  name="memberload" id="memberload" list="%{memberloadList}" 
                                               listKey="key" listValue="value"  onchange="loadMemChildreans(this.value)"  headerKey="-1"    headerValue="---Select---"     cssClass="dropdown" /></td>                           
                                    <td width="25px;"></td>
                 
                                </tr>    
                                <tr>
                                    <td class="formLable">Child Name<span class="mandatory">*</span></td> <td >:</td>
                                    <td><s:textfield id="childName" name="childName" cssClass="textField" /></td>
                                    <td width="25px;"></td>
                                    <td class="formLable">Date Of Birth</td> <td >:</td>
                                    <td><sj:datepicker id="childDob" name="childDob" readonly="true" value="today"   changeYear="true"  changeMonth = "true" yearRange = "1950" buttonImageOnly="true" displayFormat="yy-mm-dd" cssClass="textField"  /></td>
									<td width="25px;"></td>
                                    <td class="formLable">Gender<span class="mandatory">*</span></td> <td >:</td>
                                    <td><s:select  name="childGender" id="childGender"  headerKey="-1" 
                                               headerValue="---Select---"  list="%{childGenderList}"  cssClass="dropdown" /></td>                                    
                                    <td width="25px;"></td>
									
								</tr>
                                <tr>
                                    <td class="formLable">married Status<span class="mandatory">*</span></td> <td>:</td>
                                    <td><s:select  name="childMerStatus" id="childMerStatus"  headerKey="-1" 
                                               headerValue="---Select---"  list="%{childMerStatusList}"  cssClass="dropdown" /></td> 
									<td width="25px;"></td>
                                    <td class="formLable">Education</td> <td >:</td>
                                    <td><s:textfield id="childEdu" name="childEdu" cssClass="textField" /></td>
                                    <td width="25px;"></td>
                                    <td class="formLable">Address</td> <td >:</td>
                                    <td><s:textfield id="childAddr" name="childAddr" cssClass="textField" /></td>
                                    <td width="25px;"></td>
                                </tr>
                                <tr>
                                    <td class="formLable">Phone</td> <td >:</td>
                                    <td><s:textfield id="childPhone" name="childPhone" cssClass="textField" /></td>
                                    <td width="25px;"></td>
                                    <td class="formLable">Mobile</td> <td >:</td>
                                    <td><s:textfield id="childMobile" name="childMobile" cssClass="textField" /></td>
                                    <td width="25px;"></td>
                                
                                    <td class="formLable">Email</td> <td >:</td>
                                    <td><s:textfield id="childEmail" name="childEmail" cssClass="textField" /></td>
                                    <td width="25px;"></td>
                               
                                </tr>
  
                                <tr>
                                    <td class="content_td formLable" colspan="7"><span class="mandatory_text">Mandatory fields are marked with</span><span class="mandatory">*</span></td>
                                </tr>
                            </table><table class="form_table">
                                </br>
                                <tr>                                
                                    <td> <s:url var="addurl" action="AddchildMng"/>                                   
                                        <sj:submit   button="true" href="%{addurl}" value="Add"   targets="divmsg"  cssClass="button_sadd" disabled="#vadd"/> 
                                        <sj:submit id="resetida" button="true" value="Reset" onclick="ResetAddForm()"   cssClass="button_aback" disabled="false" />

                                    </td>
                                </tr>
                            </table>
                        </s:form>



                        <s:form id="editForm"  theme="simple" method="post" cssStyle="display:none" >
                            <table class="form_table">

                                <tr>
                                    <s:textfield id="childId" name="childId" cssClass="textField" hidden="true"/>
                                    <td class="formLable">Select Member-<span class="mandatory">*</span></td>                                     <td>:</td>
                                    <td><s:select  name="upmemberload" id="upmemberload" list="%{memberloadList}" 
                                               listKey="key" listValue="value"  onchange="loadMemChildreans(this.value)"  headerKey="-1"    headerValue="---Select---"     cssClass="dropdown" /></td>                           
                                    <td width="25px;"></td>
                 
                                </tr>    
                                <tr>
                                    <td class="formLable">Child Name<span class="mandatory">*</span></td> <td >:</td>
                                    <td><s:textfield id="upchildName" name="upchildName" cssClass="textField" /></td>
                                    <td width="25px;"></td>
                                    <td class="formLable">Date Of Birth</td> <td >:</td>
                                    <td><sj:datepicker id="upchildDob" name="upchildDob" readonly="true" value="today"   changeYear="true"  changeMonth = "true" yearRange = "1950" buttonImageOnly="true" displayFormat="yy-mm-dd" cssClass="textField"  /></td>
									<td width="25px;"></td>
                                
                                    <td class="formLable">Gender<span class="mandatory">*</span></td> <td >:</td>
                                    <td><s:select  name="upchildGender" id="upchildGender"  headerKey="-1" 
                                               headerValue="---Select---"  list="%{childGenderList}"  cssClass="dropdown" /></td>                                    
                                    <td width="25px;"></td>
								</tr>
                                <tr>
                                    <td class="formLable">married Status<span class="mandatory">*</span></td> <td>:</td>
                                    <td><s:select  name="upchildMerStatus" id="upchildMerStatus"  headerKey="-1" 
                                               headerValue="---Select---"  list="%{childMerStatusList}"  cssClass="dropdown" /></td> 
									<td width="25px;"></td>
                                    <td class="formLable">Education</td> <td >:</td>
                                    <td><s:textfield id="upchildEdu" name="upchildEdu" cssClass="textField" /></td>
                                    <td width="25px;"></td>
                                    <td class="formLable">Address</td> <td >:</td>
                                    <td><s:textfield id="upchildAddr" name="upchildAddr" cssClass="textField" /></td>
                                    <td width="25px;"></td>
                                </tr>
                                <tr>
                                    <td class="formLable">Phone</td> <td >:</td>
                                    <td><s:textfield id="upchildPhone" name="upchildPhone" cssClass="textField" /></td>
                                    <td width="25px;"></td>
                                    <td class="formLable">Mobile</td> <td >:</td>
                                    <td><s:textfield id="upchildMobile" name="upchildMobile" cssClass="textField" /></td>
                                    <td width="25px;"></td>
                                
                                    <td class="formLable">Email</td> <td >:</td>
                                    <td><s:textfield id="upchildEmail" name="upchildEmail" cssClass="textField" /></td>
                                    <td width="25px;"></td>
                               
                                </tr>
                                <tr>
                                    <td class="content_td formLable" colspan="7"><span class="mandatory_text">Mandatory fields are marked with</span><span class="mandatory">*</span></td>
                                </tr>
                            </table><table class="form_table">
                                </br>
                                <tr>                                
                                    <td> <s:url var="updateuserurl" action="UpdatechildMng"/>                                   
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

                            <s:url var="listurl" action="ListchildMng"/>
                            <sjg:grid
                                id="gridtable"
                                caption="Childran Management"
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
                                
                                <sjg:gridColumn name="mem_id" index="MEM_ID" title="Member ID"  frozen="false" hidden="true"/>
                                
                                <sjg:gridColumn name="chile_id" index="ID" title="Children Id" align="left" width="100" frozen="false" sortable="true"/>
                                <sjg:gridColumn name="mem_id_des" index="MEM_ID" title="Member ID" align="left" width="100" frozen="false" sortable="true"/>
                                <sjg:gridColumn name="child_name" index="CHILD_NAME" title="Childran Name" align="left" width="150" sortable="true"/>                    
                                <sjg:gridColumn name="child_dob" index="CHILD_DOB" title="Date Of Birth" align="left"  width="100"  sortable="true"/>
                                <sjg:gridColumn name="child_gender" index="GENDER" title="Gender" align="left"  width="100"  sortable="true"/>
                                <sjg:gridColumn name="child_merrid_status" index="MARRIED_STATUS" title="Married Status" align="left" width="100" sortable="true"/>

                                <sjg:gridColumn name="childEdu" index="EDUCATION" title="Education" align="left" width="150" sortable="true"/>
                                <sjg:gridColumn name="childAddr" index="ADDRESS" title="Address" align="left" width="150" sortable="true"/>

                                

                                <sjg:gridColumn name="chile_id" index="ID" title="Edit" align="center" width="80" align="center"  formatter="editformatter" sortable="false" hidden="#vupdate"/>
                                <sjg:gridColumn name="chile_id" index="ID" title="Delete" align="center" width="80" align="center"   formatter="deleteformatter" sortable="false" hidden="#vdelete"/>

                            </sjg:grid> 

                        </div> 



                    </div>
                </div>              

            </div>
        </div>
        <jsp:include page="../../footer.jsp" /> 

    </body>
</html>
