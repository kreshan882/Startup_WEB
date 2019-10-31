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
            
            function ResetAddForm() {
                resetData();
                $('#divmsg').empty();
            }
            function resetData() {
                $('#memName').val("");
                $('#memNic').val("");
                $('#memDob').val("");
                $('#phoneNo').val("");
                $('#mobileNo').val("");
                $('#email').val("");
                
                $('#qualification').val("");
                $('#perAddress').val("");
                $('#temAddress').val("");
                
                $('#memBornPlace').val("");
                $('#memCast').val("-1");
                $('#memSubCast').val("");

                $('#memIslife').val("-1");
                $('#noOfBrother').val("0");
                $('#noOfSister').val("0");

                $('#jobTitle').val("");
                $('#jobAddress').val("");
                $('#jobPhone').val("");
                
                $('#fatName').val("");
                $('#fatBirthPlace').val("");
                $('#fatCast').val("");
                $('#mothName').val("");
                $('#mothBirthPlace').val("");
                $('#mothCast').val("");
                
                $('#grandFatName').val("");
                $('#grandFatBirthPlace').val("");
                $('#grandFatCast').val("");
                $('#grandMothName').val("");
                $('#grandMothBirthPlace').val("");
                $('#grandMothCast').val("");
                
                $('#isMerrid').val("-1");
                $('#wifeName').val("");
                $('#noOfSuns').val("0");
                $('#noOfDoters').val("0");
                
                $('#wifeDob').val("");
                $('#wifeAdd').val("");
                $('#wifeEmail').val("");
                $('#wifeMobile').val("");
                $('#wifeBirPlace').val("");
                $('#wifeCast').val("");
                
                $('#wifeFatName').val("");
                $('#wifeFatBirthPlace').val("");
                $('#wifeFatCast').val("");
                $('#wifeMothName').val("");
                $('#wifeMothBirthPlace').val("");
                $('#wifeMothCast').val("");
                
                $('#wifeGrandFatName').val("");
                $('#wifeGrandFatBirthPlace').val("");
                $('#wifeGrandFatCast').val("");
                $('#wifeGrandMothName').val("");
                $('#wifeGrandMothBirthPlace').val("");
                $('#wifeGrandMothCast').val("");
                
                $('#memImgFileFileName').val("");
                $('#famImgFileFileName').val("");
                
                //$('#memIdDes').val("");
                
            }

            






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


                        <s:form  id="addForm"  theme="simple" enctype="multipart/form-data" method="post" >
  
                            <table class="form_table">
                                <tr>
                                    <td class="formLable">Member ID<span class="mandatory">*</span></td> <td >:</td>
                                    <td><s:textfield id="memIdDes" name="memIdDes" readonly="true" cssClass="textField" /></td>                                    
                                    <td width="25px;"></td>
                                    
                                </tr> 
                                <tr>
                                    <td class="formLable">Member Name<span class="mandatory">*</span></td> <td >:</td>
                                    <td><s:textfield id="memName" name="memName" cssClass="textField" /></td>                                    
                                    <td width="25px;"></td>
                                    <td class="formLable">Member NIC<span class="mandatory">*</span></td> <td>:</td>
                                    <td><s:textfield id="memNic" name="memNic" cssClass="textField" /></td> 
                                    <td width="25px;"></td>
                                    <td class="formLable">Member DOB<span class="mandatory">*</span></td> <td>:</td>
                                    <td><sj:datepicker id="memDob" name="memDob" readonly="true" value="today"   changeYear="true"  changeMonth = "true" yearRange = "1950" buttonImageOnly="true" displayFormat="yy-mm-dd" cssClass="textField"  /></td>
                                </tr>    
                          
                                <tr>
                                    <td class="formLable">Land Phone Number<span class="mandatory">*</span></td> <td >:</td>
                                    <td><s:textfield id="phoneNo" name="phoneNo" cssClass="textField" placeholder="0117123456"/></td>                                    
                                    <td width="25px;"></td>
                                    <td class="formLable">Mobile Number<span class="mandatory">*</span></td> <td>:</td>
                                    <td><s:textfield id="mobileNo" name="mobileNo" cssClass="textField" placeholder="0777123456"/></td>  
                                    <td width="25px;"></td>
                                    <td class="formLable">Email</td> <td>:</td>
                                    <td><s:textfield id="email" name="email" cssClass="textField" /></td> 
                                </tr> 
                                
                                <tr>
                                    <td class="formLable">Qualification</td> <td >:</td>
                                    <td><s:textfield id="qualification" name="qualification" cssClass="textField" /></td>                                    
                                    <td width="25px;"></td>
                                    <td class="formLable">Permanent Address</td> <td>:</td>
                                    <td><s:textfield id="perAddress" name="perAddress" cssClass="textField" /></td>  
                                    <td width="25px;"></td>
                                    <td class="formLable">Temporary Address</td> <td>:</td>
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
                                    <td class="formLable" id="memExpdate1" >Exp Date</td> <td id="memExpdate2">:</td>
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
                                    <td class="formLable">Occupation</td> <td >:</td>
                                    <td><s:textfield id="jobTitle" name="jobTitle" cssClass="textField" /></td>                                    
                                    <td width="25px;"></td>
                                    <td class="formLable">Office Address</td> <td>:</td>
                                    <td><s:textfield id="jobAddress" name="jobAddress" cssClass="textField" /></td>  
                                    <td width="25px;"></td>
                                    <td class="formLable">Office Phone</td> <td>:</td>
                                    <td><s:textfield id="jobPhone" name="jobPhone" cssClass="textField" /></td> 
                                </tr>
                                </table>
                                
                                <fieldset style="background-color:rgb(245,249,249)">
                                    <legend>Parent Details</legend>
                                    <table>
                                    <tr>
                                        <td class="formLable">Father's Name<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:textfield id="fatName" name="fatName" cssClass="textField" /></td>                                    
                                        <td width="25px;"></td>
                                        <td class="formLable">Father's Birth Place</td> <td>:</td>
                                        <td><s:textfield id="fatBirthPlace" name="fatBirthPlace" cssClass="textField" /></td>  
                                        <td width="25px;"></td>
                                        <td class="formLable">Father's Cast<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield  name="fatCast" id="fatCast"  cssClass="textField" /></td> 
                                    </tr>
                                    <tr>
                                        <td class="formLable">Mother's Name<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:textfield id="mothName" name="mothName" cssClass="textField" /></td>                                    
                                        <td width="25px;"></td>
                                        <td class="formLable">Mother's Birth Place</td> <td>:</td>
                                        <td><s:textfield id="mothBirthPlace" name="mothBirthPlace" cssClass="textField" /></td>  
                                        <td width="25px;"></td>
                                        <td class="formLable">Mother's Cast<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield  name="mothCast" id="mothCast"  cssClass="textField" /></td> 
                                    </tr>
                                    
                                    <tr>
                                        <td class="formLable">Grandfather's Name<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:textfield id="grandFatName" name="grandFatName" cssClass="textField" /></td>                                    
                                        <td width="25px;"></td>
                                        <td class="formLable">Grandfather's Birth Place</td> <td>:</td>
                                        <td><s:textfield id="grandFatBirthPlace" name="grandFatBirthPlace" cssClass="textField" /></td>  
                                        <td width="25px;"></td>
                                        <td class="formLable">Grandfather's Cast<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield  name="grandFatCast" id="grandFatCast"  cssClass="textField" /></td> 

                                    </tr>
                                    <tr>
                                        <td class="formLable">Grandmother's Name<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:textfield id="grandMothName" name="grandMothName" cssClass="textField" /></td>                                    
                                        <td width="25px;"></td>
                                        <td class="formLable">Grandmother's Birth Place</td> <td>:</td>
                                        <td><s:textfield id="grandMothBirthPlace" name="grandMothBirthPlace" cssClass="textField" /></td>  
                                        <td width="25px;"></td>
                                        <td class="formLable">Grandmother's Cast<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield  name="grandMothCast" id="grandMothCast"  cssClass="textField" /></td> 
                                        
                                    </tr>
                                    
                                    
                                    </table>
                                </fieldset>
                                
                            <table>  
                                <tr>
                                        <td class="formLable">Married Status<span class="mandatory">*</span></td> <td >:</td>
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
                                        <td class="formLable">Num of Son's<span class="mandatory">*</span></td> <td>:</td> 
                                        <td><s:select  name="noOfSuns" id="noOfSuns"  list="%{numberList}"  cssClass="dropdown"  /></td>
                                        <td width="25px;"></td>
                                        <td class="formLable">Num of Daughter's<span class="mandatory">*</span></td> <td>:</td> 
                                        <td><s:select  name="noOfDoters" id="noOfDoters"  list="%{numberList}"  cssClass="dropdown" /></td>
                                    </tr>
                                    <tr>
                                        <td class="formLable">DOB<span class="mandatory">*</span></td> <td >:</td>
                                        <td><sj:datepicker id="wifeDob" name="wifeDob" readonly="true" value="today"   changeYear="true"  changeMonth = "true" yearRange = "1900" buttonImageOnly="true" displayFormat="yy-mm-dd" cssClass="textField"  /></td>                                  
                                        <td width="25px;"></td>
                                        <td class="formLable">Address</td> <td>:</td> 
                                        <td><s:textfield id="wifeAdd" name="wifeAdd" cssClass="textField" /></td> 
                                        <td width="25px;"></td>
                                        <td class="formLable">Email</td> <td>:</td> 
                                        <td><s:textfield id="wifeEmail" name="wifeEmail" cssClass="textField" /></td> 
                                    </tr>
                                    <tr>
                                        <td class="formLable">Mobile<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:textfield id="wifeMobile" name="wifeMobile" cssClass="textField" /></td>                                     
                                        <td width="25px;"></td>
                                        <td class="formLable">Wife's Birth Place<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield id="wifeBirPlace" name="wifeBirPlace" cssClass="textField" /></td>  
                                        <td width="25px;"></td>
                                        <td class="formLable">Wife's Cast<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield  name="wifeCast" id="wifeCast"  cssClass="textField" /></td> 
                                    </tr>
                                    <tr>
                                        <td class="formLable">Wife Father's Name<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:textfield id="wifeFatName" name="wifeFatName" cssClass="textField" /></td>                                    
                                        <td width="25px;"></td>
                                        <td class="formLable">Wife Father's Birth Place<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield id="wifeFatBirthPlace" name="wifeFatBirthPlace" cssClass="textField" /></td>  
                                        <td width="25px;"></td>
                                        <td class="formLable">Wife Father Cast<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield  name="wifeFatCast" id="wifeFatCast"  cssClass="textField" /></td> 
                                    </tr>
                                    <tr>
                                        <td class="formLable">Wife Mother's Name<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:textfield id="wifeMothName" name="wifeMothName" cssClass="textField" /></td>                                    
                                        <td width="25px;"></td>
                                        <td class="formLable">Wife Mother's Birth Place<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield id="wifeMothBirthPlace" name="wifeMothBirthPlace" cssClass="textField" /></td>  
                                        <td width="25px;"></td>
                                        <td class="formLable">Wife Mother's Cast<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield  name="wifeMothCast" id="wifeMothCast"  cssClass="textField" /></td> 
                                    </tr>
                                    
                                    <tr>
                                        <td class="formLable">Wife Grandfather's Name<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:textfield id="wifeGrandFatName" name="wifeGrandFatName" cssClass="textField" /></td>                                    
                                        <td width="25px;"></td>
                                        <td class="formLable">Wife Grandfather's Birth Place<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield id="wifeGrandFatBirthPlace" name="wifeGrandFatBirthPlace" cssClass="textField" /></td>  
                                        <td width="25px;"></td>
                                        <td class="formLable">Wife Grandfather's Cast<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield  name="wifeGrandFatCast" id="wifeGrandFatCast"  cssClass="textField" /></td> 
                                    </tr>
                                    <tr>
                                        <td class="formLable">Wife Grandmother's Name<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:textfield id="wifeGrandMothName" name="wifeGrandMothName" cssClass="textField" /></td>                                    
                                        <td width="25px;"></td>
                                        <td class="formLable">Wife Grandmother's Birth Place<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield id="wifeGrandMothBirthPlace" name="wifeGrandMothBirthPlace" cssClass="textField" /></td>  
                                        <td width="25px;"></td>
                                        <td class="formLable">Wife Grandmother's Cast<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield  name="wifeGrandMothCast" id="wifeGrandMothCast"  cssClass="textField" /></td> 
                                    </tr>
                                    </table>
                                </fieldset>
								
							<fieldset id="familyId" style="background-color:rgb(245,249,249)">
                                    <legend>Upload Images</legend>
                                    <table>
                                    <tr>
                                        <td class="formLable">Member Photo<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:file  id = "memImgFile" name="memImgFile" label="File" cssClass="fileField"  /></td>                                    
                                        <td width="25px;"></td>
                                    </tr>
                                    <tr>
                                        <td class="formLable">Member Family Photo<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:file  id = "famImgFile" name="famImgFile" label="File" cssClass="fileField"  /></td>                                    
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

                                    </td>
                                </tr>
                            </table>
                        </s:form>
<%--
			<s:form id="addFile" action="UploadFilebulkMsgMng-remove"  theme="simple" enctype="multipart/form-data"  method="post" >         
                            <table class="form_table" style="margin-top: 5px;" border="0px"> 
                                <tr style=" height: 19px;">
                                    <s:textfield id="memId" name="memId"  cssClass="textField" />
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

  --%>
                    </div>



                </div>              

            </div>
        </div>
        <jsp:include page="../../footer.jsp" /> 

    </body>
</html>

