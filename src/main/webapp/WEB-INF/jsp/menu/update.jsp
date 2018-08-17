<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            	修改菜单
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form role="form" id="menuUpdateForm">
                                    	<div class="form-group">
                                            <label>ID</label>
                                            <input class="form-control"  name="id" value="${menu.id}" >
                                        </div>
                                        <div class="form-group">
                                            <label>菜单名</label>
                                            <input class="form-control"  name="name" value="${menu.name}" >
                                            <input type="hidden" name="parent.id" value="${parentId }">
                                        </div>
                                        <div class="form-group">
                                            <label>URL</label>
                                            <input class="form-control"  name="url" value="${menu.url}" >
                                        </div>
                                        <div class="form-group">
                                            <label>图标</label>
                                            <input class="form-control"  name="icon" value="${menu.icon}" >
                                        </div>
                                       	<div class="form-group">
                                            <label>编号</label>
                                            <input class="form-control"  name="no" value="${menu.no}">
                                        </div>
                                        <div class="form-group">
                                            <label>上级</label>
                                            <input class="form-control"  name="parentName" value="${menu.parentName}">
                                        </div>
                                        
                                        <button type="button" onclick="submitMenuUpdateForm ();" class="btn btn-default">提交</button>
                                        <button type="reset" class="btn btn-default">重置</button>
                                    </form>
                                </div>
                               
                            </div>
                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
                <script type="text/javascript">
                	function submitMenuUpdateForm () {
                		$.post ('${pageContext.request.contextPath}/menu/update', $('#menuUpdateForm').serializeArray(), function (result) {
                			if (result.success) {
                				$('#page-wrapper').load ('${pageContext.request.contextPath}/menu');
                			} else {
                				alert (result.message);
                			}
                		});
                	}
                
                	
                </script>
            </div>
