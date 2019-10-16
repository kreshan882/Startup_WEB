<%-- 
    Document   : memberSummary
    Created on : Oct 16, 2019, 11:05:25 PM
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

            
            function backToMain() {
                $('#editForm').hide();
                $('#pwresetForm').hide();
                $('#searchForm').show();
                $('#addForm').hide();

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
                <div class="heading">Member Summary Report</div>
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
                           
                    </s:form>

                       
                    </div>

                    <div class="viewuser_tbl">
                        <div id="tablediv">


                            <s:url var="listurl" action="ListtotMemSummary"/>
                            <sjg:grid
                                id="gridtable"
                                caption="Member Summary Report"
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
                                
                                
                                <sjg:gridColumn name="castName" index="CAST_NAME" title="Cast Name" align="center" width="600" frozen="false" sortable="true"/>
                                <sjg:gridColumn name="castCount" index="MEM_COUNT" title="Cast COUNT" align="center" width="600" sortable="true"/>                    
                                
                          </sjg:grid> 

                        </div> 



                    </div>
                </div>              

            </div>
        </div>
        <jsp:include page="../../footer.jsp" /> 

    </body>
</html>
