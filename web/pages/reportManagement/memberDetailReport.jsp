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


            
            function downloadformatter(cellvalue, options, rowObject) {
                return "<a href='#' onClick='javascript:downloadNow(&#34;" + cellvalue + "&#34;)'><img src ='${pageContext.request.contextPath}/resources/images/download.png' /></a>";
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
                var memId = $('#memId').val();
                var memCastID = $('#memCastID').val();
                $("#gridtable").jqGrid('setGridParam', {postData: {memId: memId,memCastID: memCastID, search: true}});
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
                    <s:form id="searchForm" action="usrMng" method="post" action="DownloadtotMemReport" theme="simple">         
                            <table class="form_table">              
                                <tr>
                                    <td class="content_td formLable">Member Id</td>
                                    <td>:</td>
                                    <td colspan="2"><s:textfield name="memId"  id="memId" cssClass="textField" /> </td>
                                    <td class="content_td formLable">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="content_td formLable">Member Cast</td>
                                    <td>:</td>
                                    <td><s:select  name="memCastID" id="memCastID"  headerKey="" 
                                               headerValue="All Cast"  list="%{memCastList}"  cssClass="dropdown" /></td> 
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

                       
                    </div>


                    <div class="viewuser_tbl">
                        <div id="tablediv">


                            <s:url var="listurl" action="ListtotMemReport"/>
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
                                
                                <sjg:gridColumn name="memId" index="MEM_ID" title="MemberId"  frozen="false" hidden="true"/>
                                
                                <sjg:gridColumn name="memIdDes" index="MEM_ID" title="Member ID" align="left" width="100" frozen="false" sortable="true"/>
                                <sjg:gridColumn name="memName" index="MEM_NAME" title="Name" align="left" width="100" sortable="true"/>                    
                                <sjg:gridColumn name="perAddr" index="PERM_ADD" title="Permenant Add" align="left"  width="150"  sortable="true"/>
                                <sjg:gridColumn name="temAddr" index="TEMP_ADD" title="Tempoary Add" align="left"  width="150"  sortable="true"/>
                                <sjg:gridColumn name="offAddr" index="JOB_ADD" title="Office Add" align="left" width="150" sortable="true"/>
                                <sjg:gridColumn name="tpNum" index="MEM_PHONE" title="TP Number" align="left" width="100" sortable="true"/>
                                <sjg:gridColumn name="mobileNum" index="MEM_MOBILE" title="Mobile Number" align="left" width="100" sortable="true"/>
                                <sjg:gridColumn name="offPhnNum" index="JOB_PHONE" title="Office Number" align="left" width="100" sortable="true"/>
                                <sjg:gridColumn name="memCast" index="CAST_NAME" title="Cast" align="left" width="100" sortable="true"/>
                                <sjg:gridColumn name="regDate" index="CREATE_DATE" title="Reg Date" align="center"  width="100"  sortable="true"/>
                                
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
