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
                $('#noOfSister').val("");

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
                return "<a href='#' onClick='deleteInit(&#34;" + rowObject.memId + "&#34;,&#34;" + rowObject.memIdDes + "&#34;)'><img src='${pageContext.request.contextPath}/resources/images/iconDelete.png'  /></a>";
            }

            function editformatter(cellvalue, options, rowObject) {
                return "<a href='#' onClick='javascript:editNow(&#34;" + cellvalue + "&#34;)'><img src ='${pageContext.request.contextPath}/resources/images/iconEdit.png' /></a>";
            }
            
            function downloadformatter(cellvalue, options, rowObject) {
                return "<a href='#' onClick='javascript:downloadInit(&#34;" + rowObject.memId + "&#34;,&#34;" + rowObject.memIdDes + "&#34;)'><img src ='${pageContext.request.contextPath}/resources/images/download.png' /></a>";
            }

            function downloadInit(keyval,keyvaldes) {
                $("#confirmdialogboxDownload").data('keyval', keyval).dialog('open');
                $("#confirmdialogboxDownload").html('<br><b><font size="3" color="red"><center>Please confirm to download member id : ' + keyvaldes + '');
                return false;
            }

            function deleteInit(keyval,keyvaldes) {
                $("#confirmdialogbox").data('keyval', keyval).dialog('open');
                $("#confirmdialogbox").html('<br><b><font size="3" color="red"><center>Please confirm to delete member id : ' + keyvaldes + '');
                return false;
            }



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
                        //alert("data.memId"+data.memId);
                        //alert("data.memIdDes"+data.memIdDes);
                        $('#editForm').show();
                        $('#searchForm').hide();

                        $('#upusername').val(data.upusername);
                        $('#upusername').attr('readOnly', true).val();
                        
                        $('#memId').val(data.memId); //5
                        $('#memIdUp').val(data.memId); 
                        $('#status').val(data.status);
                        
                        
                        
                        
                        $('#memIdDes').val(data.memIdDes); //M00005
                        $('#memName').val(data.memName);
                        $('#memNic').val(data.memNic);
                        $('#memDob').val(data.memDob);
                        
                        $('#phoneNo').val(data.phoneNo);
                        $('#mobileNo').val(data.mobileNo);
                        $('#email').val(data.email);
                        $('#qualification').val(data.qualification);
                        $('#perAddress').val(data.perAddress);
                        
                        $('#temAddress').val(data.temAddress);
                        $('#memBornPlace').val(data.memBornPlace);
                        $('#memCast').val(data.memCast);
                        $('#memSubCast').val(data.memSubCast);
                        $('#memIslife').val(data.memIslife);
                        
                        $('#memExpdate').val(data.memExpdate);
                        $('#noOfBrother').val(data.noOfBrother);
                        $('#noOfSister').val(data.noOfSister);
                        $('#jobTitle').val(data.jobTitle);
                        $('#jobAddress').val(data.jobAddress);
                        
                        $('#jobPhone').val(data.jobPhone);
                        
                        //parent details
                        $('#fatName').val(data.fatName);
                        $('#fatBirthPlace').val(data.fatBirthPlace);
                        $('#fatCast').val(data.fatCast);
                        $('#mothName').val(data.mothName);
                        $('#mothBirthPlace').val(data.mothBirthPlace);
                        $('#mothCast').val(data.mothCast);
                        
                        $('#grandFatName').val(data.grandFatName);
                        $('#grandFatBirthPlace').val(data.grandFatBirthPlace);
                        $('#grandFatCast').val(data.grandFatCast);
                        $('#grandMothName').val(data.grandMothName);
                        $('#grandMothBirthPlace').val(data.grandMothBirthPlace);
                        $('#grandMothCast').val(data.grandMothCast);
                        
                        //sponced detail
                        
                        $('#isMerrid').val(data.isMerrid);
                        
                        $('#wifeName').val(data.wifeName);
                        $('#noOfSuns').val(data.noOfSuns);
                        $('#noOfDoters').val(data.noOfDoters);
                        
                        $('#wifeDob').val(data.wifeDob);    
                        $('#wifeAdd').val(data.wifeAdd);
                        $('#wifeEmail').val(data.wifeEmail);
                        
                        $('#wifeMobile').val(data.wifeMobile);
                        $('#wifeBirPlace').val(data.wifeBirPlace);
                        $('#wifeCast').val(data.wifeCast);
                        
                        $('#wifeFatName').val(data.wifeFatName);
                        $('#wifeFatBirthPlace').val(data.wifeFatBirthPlace);
                        $('#wifeFatCast').val(data.wifeFatCast);
                        
                        $('#wifeMothName').val(data.wifeMothName);
                        $('#wifeMothBirthPlace').val(data.wifeMothBirthPlace);
                        $('#wifeMothCast').val(data.wifeMothCast);
                        
                        $('#wifeGrandFatName').val(data.wifeGrandFatName);
                        $('#wifeGrandFatBirthPlace').val(data.wifeGrandFatBirthPlace);
                        $('#wifeGrandFatCast').val(data.wifeGrandFatCast);
                        
                        $('#wifeGrandMothName').val(data.wifeGrandMothName);
                        $('#wifeGrandMothBirthPlace').val(data.wifeGrandMothBirthPlace);
                        $('#wifeGrandMothCast').val(data.wifeGrandMothCast);
                        
                        //$('#memImgFileFileName').val(data.memImgFileFileName);
                        //$('#famImgFileFileName').val(data.famImgFileFileName);

                        
                        var src = document.getElementById("memImgFileFileName");
                        src.setAttribute("src", "/imagesK/members/"+data.memImgFileFileName);
                        
                        var src = document.getElementById("famImgFileFileName");
                        src.setAttribute("src", "/imagesK/members/"+data.famImgFileFileName);

                    },
                    error: function (data) {
                        window.location = "${pageContext.request.contextPath}/LogoutloginCall.blb?";
                    }
                });
            }
            
            function downloadNow(keyval) {
                //alert("download:"+keyval)
                
                $('#memId').val(keyval);
                document.getElementById("searchForm").submit();

            }


            function backToMain() {
                $('#editForm').hide();
                $('#searchForm').show();

                $('#divmsg').empty();
                jQuery("#gridtable").trigger("reloadGrid");

            }




            $.subscribe('onclicksearch', function (event, data) {
                var searchname = $('#searchname').val();
                $("#gridtable").jqGrid('setGridParam', {postData: {searchname: searchname, search: true}});
                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");
            });



            //reset Datas
            function ResetSearchForm() {
                $('#searchname').val("");
                $('#divmsg').empty();
                jQuery("#gridtable").trigger("reloadGrid");
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
                                        <s:textfield name="memId"  id="memId" cssClass="textField" hidden="true"/>
                                    </td>
                                </tr>
                            </table><table class="form_table">
                                </br>
                                <tr>                                
                                    <td> 

                                        <sj:a id="searchid"  button="true"    onClickTopics="onclicksearch"  cssClass="button_asave"  role="button" aria-disabled="false" >Search</sj:a>
                                        <sj:a id="refreshbut"  button="true"  onClickTopics="grideReload"  cssClass="button_asave">Refresh</sj:a>  
                                       <%-- <sj:submit button="true" cssStyle="font-size:12px;padding: 5px 5px;height:27; width:106px;" cssClass="button_asave" value="Print PDF" />  --%>
                                    </td>
                                </tr>
                            </table>
                    </s:form>

                    <s:form id="editForm"  theme="simple" method="post" cssStyle="display:none" >
                            <table class="form_table">
                                <tr>
                                    <td class="formLable">Member ID<span class="mandatory">*</span></td> <td >:</td>
                                    <td><s:textfield id="memIdDes" name="memIdDes" readonly="true" cssClass="textField" /></td>                                    
                                    <td width="25px;"></td>
                                    <s:textfield name="memIdUp"  id="memIdUp" cssClass="textField" hidden="true"/>
                                    <td class="formLable">Status<span class="mandatory">*</span></td><td>:</td>
                                    <td ><s:select  name="status" id="status" list="%{statusList}" 
                                               listKey="key" listValue="value"    headerKey="-1"    headerValue="---Select---"     cssClass="dropdown" />
                                    </td>
                                    <td width="25px"></td>
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
                                    <td><s:textfield id="phoneNo" name="phoneNo" cssClass="textField" placeholder="0117123456"/></td>                                    
                                    <td width="25px;"></td>
                                    <td class="formLable">Mobile Number<span class="mandatory">*</span></td> <td>:</td>
                                    <td><s:textfield id="mobileNo" name="mobileNo" cssClass="textField" placeholder="0777123456"/></td>  
                                    <td width="25px;"></td>
                                    <td class="formLable">Email</td> <td>:</td>
                                    <td><s:textfield id="email" name="email" cssClass="textField" /></td> 
                                </tr> 
                                
                                <tr>
                                    <td class="formLable">Qualification<span class="mandatory">*</span></td> <td >:</td>
                                    <td><s:textfield id="qualification" name="qualification" cssClass="textField" /></td>                                    
                                    <td width="25px;"></td>
                                    <td class="formLable">Permanent Address<span class="mandatory">*</span></td> <td>:</td>
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
                                        <td class="formLable">Father Name<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:textfield id="fatName" name="fatName" cssClass="textField" /></td>                                    
                                        <td width="25px;"></td>
                                        <td class="formLable">Father Birth Place</td> <td>:</td>
                                        <td><s:textfield id="fatBirthPlace" name="fatBirthPlace" cssClass="textField" /></td>  
                                        <td width="25px;"></td>
                                        <td class="formLable">Father Cast<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield  name="fatCast" id="fatCast"  cssClass="textField" /></td> 
                                    </tr>
                                    <tr>
                                        <td class="formLable">Mother Name<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:textfield id="mothName" name="mothName" cssClass="textField" /></td>                                    
                                        <td width="25px;"></td>
                                        <td class="formLable">Mother Birth Place</td> <td>:</td>
                                        <td><s:textfield id="mothBirthPlace" name="mothBirthPlace" cssClass="textField" /></td>  
                                        <td width="25px;"></td>
                                        <td class="formLable">Mother Cast<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield  name="mothCast" id="mothCast"  cssClass="textField" /></td> 
                                    </tr>
                                    
                                    <tr>
                                        <td class="formLable">Grandfather Name<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:textfield id="grandFatName" name="grandFatName" cssClass="textField" /></td>                                    
                                        <td width="25px;"></td>
                                        <td class="formLable">Grandfather Birth Place</td> <td>:</td>
                                        <td><s:textfield id="grandFatBirthPlace" name="grandFatBirthPlace" cssClass="textField" /></td>  
                                        <td width="25px;"></td>
                                        <td class="formLable">Grandfather Cast<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield  name="grandFatCast" id="grandFatCast"  cssClass="textField" /></td> 

                                    </tr>
                                    <tr>
                                        <td class="formLable">Grandmother Name<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:textfield id="grandMothName" name="grandMothName" cssClass="textField" /></td>                                    
                                        <td width="25px;"></td>
                                        <td class="formLable">Grandmother Birth Place</td> <td>:</td>
                                        <td><s:textfield id="grandMothBirthPlace" name="grandMothBirthPlace" cssClass="textField" /></td>  
                                        <td width="25px;"></td>
                                        <td class="formLable">Grandmother Cast<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield  name="grandMothCast" id="grandMothCast"  cssClass="textField" /></td> 
                                        
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
                                        <td><sj:datepicker id="wifeDob" name="wifeDob" readonly="true" value="today"   changeYear="true"  changeMonth = "true" yearRange = "1950" buttonImageOnly="true" displayFormat="yy-mm-dd" cssClass="textField"  /></td>                                  
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
                                        <td class="formLable">Wife Birth Place<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield id="wifeBirPlace" name="wifeBirPlace" cssClass="textField" /></td>  
                                        <td width="25px;"></td>
                                        <td class="formLable">Wife Cast<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield  name="wifeCast" id="wifeCast"  cssClass="textField" /></td> 
                                    </tr>
                                    <tr>
                                        <td class="formLable">Wife Father Name<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:textfield id="wifeFatName" name="wifeFatName" cssClass="textField" /></td>                                    
                                        <td width="25px;"></td>
                                        <td class="formLable">Wife Father Birth Place<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield id="wifeFatBirthPlace" name="wifeFatBirthPlace" cssClass="textField" /></td>  
                                        <td width="25px;"></td>
                                        <td class="formLable">Wife Father Cast<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield  name="wifeFatCast" id="wifeFatCast"  cssClass="textField" /></td> 
                                    </tr>
                                    <tr>
                                        <td class="formLable">Wife Mother Name<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:textfield id="wifeMothName" name="wifeMothName" cssClass="textField" /></td>                                    
                                        <td width="25px;"></td>
                                        <td class="formLable">Wife Mother Birth Place<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield id="wifeMothBirthPlace" name="wifeMothBirthPlace" cssClass="textField" /></td>  
                                        <td width="25px;"></td>
                                        <td class="formLable">Wife Mother Cast<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield  name="wifeMothCast" id="wifeMothCast"  cssClass="textField" /></td> 
                                    </tr>
                                    
                                    <tr>
                                        <td class="formLable">Wife Grandfather Name<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:textfield id="wifeGrandFatName" name="wifeGrandFatName" cssClass="textField" /></td>                                    
                                        <td width="25px;"></td>
                                        <td class="formLable">Wife Grandfather Birth Place<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield id="wifeGrandFatBirthPlace" name="wifeGrandFatBirthPlace" cssClass="textField" /></td>  
                                        <td width="25px;"></td>
                                        <td class="formLable">Wife Grandfather Cast<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield  name="wifeGrandFatCast" id="wifeGrandFatCast"  cssClass="textField" /></td> 
                                    </tr>
                                    <tr>
                                        <td class="formLable">Wife Grandmother Name<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:textfield id="wifeGrandMothName" name="wifeGrandMothName" cssClass="textField" /></td>                                    
                                        <td width="25px;"></td>
                                        <td class="formLable">Wife Grandmother Birth Place<span class="mandatory">*</span></td> <td>:</td>
                                        <td><s:textfield id="wifeGrandMothBirthPlace" name="wifeGrandMothBirthPlace" cssClass="textField" /></td>  
                                        <td width="25px;"></td>
                                        <td class="formLable">Wife Grandmother Cast<span class="mandatory">*</span></td> <td>:</td>
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
                                        <td><img id="memImgFileFileName"  width="100" height="100"/></td>
                                    </tr>
                                    <tr
                                    <tr>
                                        <td class="formLable">Member Family Photo<span class="mandatory">*</span></td> <td >:</td>
                                        <td><s:file  id = "famImgFile" name="famImgFile" label="File" cssClass="fileField"  /></td>                                    
                                        <td width="25px;"></td>
                                        <td><img id="famImgFileFileName"  width="100" height="100"/></td>
                                        
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
                                    <td> <s:url var="updateuserurl" action="UpdateeditViewMember"/>                                   
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
                            <sj:dialog 
                                id="confirmdialogboxDownload" 
                                buttons="{ 
                                'OK':function() { downloadNow($(this).data('keyval'));$( this ).dialog( 'close' ); },
                                'Cancel':function() { $( this ).dialog( 'close' );} 
                                }" 
                                autoOpen="false" 
                                modal="true" 
                                title="Download Certificate"
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
                                
                                <sjg:gridColumn name="memId" index="MEM_ID" title="ProfileId"  frozen="false" hidden="true"/>
                                
                                <sjg:gridColumn name="memIdDes" index="MEM_ID" title="Member ID" align="left" width="150" frozen="true" sortable="true"/>
                                <sjg:gridColumn name="memName" index="MEM_NAME" title="Name" align="left" width="150" sortable="true" frozen="true" />                    
                                <sjg:gridColumn name="memNic" index="MEM_NIC" title="Nic" align="left"  width="150"  sortable="true"/>
                                <sjg:gridColumn name="memDob" index="MEM_DOB" title="Dob" align="left"  width="100"  sortable="true"/>
                                <sjg:gridColumn name="phoneNo" index="MEM_PHONE" title="Phone" align="left" width="100" sortable="true"/>
                                <sjg:gridColumn name="memBornPlace" index="MEM_BORN_PLACE" title="Born Place" align="left" width="100" sortable="true"/>
                                <sjg:gridColumn name="memCast" index="CAST_NAME" title="Cast" align="left" width="100" sortable="true"/>
                                
                                <sjg:gridColumn name="regDate" index="CREATE_DATE" title="Reg Date" align="center"  width="100"  sortable="true"/>
                                
                                <sjg:gridColumn name="status" index="STATUS" title="Status" align="center" width="80" formatter="statusformatter" sortable="true"/>  
                       
                                <%--<sjg:gridColumn name="username" index="USERNAME" title="Reset Pw" align="center" width="7" align="center"  formatter="pdchangeformatter" sortable="false" hidden="#vresetpass"/>--%>
                                <sjg:gridColumn name="memId" index="MEM_ID" title="Edit" align="center" width="80" align="center"  formatter="editformatter" sortable="false" hidden="#vupdate"/>
                                <sjg:gridColumn name="memId" index="MEM_ID" title="Delete" align="center" width="80" align="center"   formatter="deleteformatter" sortable="false" hidden="#vdelete"/>
                                <sjg:gridColumn name="memId" index="MEM_ID" title="Download" align="center" width="80" align="center"   formatter="downloadformatter" sortable="false" hidden="#vupdate"/>

                            </sjg:grid> 

                        </div> 



                    </div>
                </div>              

            </div>
        </div>
        <jsp:include page="../../footer.jsp" /> 

    </body>
</html>
