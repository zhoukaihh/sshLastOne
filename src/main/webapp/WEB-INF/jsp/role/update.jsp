<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            	修改角色
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form role="form" id="roleUpdateForm">
                                    	<div class="form-group">
                                            <label>ID</label>
                                            <input class="form-control"  name="id" value="${role.id}" >
                                        </div>
                                        <div class="form-group">
                                            <label>名称</label>
                                            <input class="form-control"  name="name" value="${role.name}" >
                                        </div>
                                       	<div class="form-group">
                                            <label>描述</label>
                                            <input class="form-control"  name="description" value="${role.description}">
                                        </div>
                                        <div class="form-group input-group">
                                        	<input type="hidden" name="menuIds" id="roleFormMenuIds" value="${role.menuIds}">
                                            <input type="text" class="form-control" value="${role.menuNames}" id="roleFormMenuNames">
                                            <span class="input-group-btn">
                                                <button class="btn btn-default" type="button" onclick="selectRoleMenus();"><i class="fa fa-search"></i>
                                                </button>
                                            </span>
                                        </div>
                                        <button type="button" onclick="submitRoleUpdateForm ();" class="btn btn-default">提交</button>
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
                	function submitRoleUpdateForm () {
                		$.post ('${pageContext.request.contextPath}/role/update', $('#roleUpdateForm').serializeArray(), function (result) {
                			if (result.success) {
                				$('#page-wrapper').load ('${pageContext.request.contextPath}/role');
                			} else {
                				alert (result.message);
                			}
                		});
                	}
                	function selectRoleMenus () {
                		$('#page-wrapper').hide().attr('id', "page-wrapper2");
                		$('#wrapper').append ('<div id="page-wrapper">菜单列表选择</div>');
                		$('#page-wrapper').load ('${pageContext.request.contextPath}/role/menus');
                	}
                	
                </script>
            </div>
