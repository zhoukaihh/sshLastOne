<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            	创建子部门
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form role="form" id="deptCreateForm">
                                    	
                                        <div class="form-group">
                                            <label>部门名称</label>
                                            <input class="form-control" placeholder="请输入名称" name="name">
                                            <input type="hidden" name="parent.id" value="${parentId }">
                                        </div>
                                       	<div class="form-group">
                                            <label>部门描述</label>
                                            <input class="form-control" placeholder="请输入描述" name="description">
                                        </div>
                                        <div class="form-group">
                                            <label>部门编号</label>
                                            <input class="form-control" placeholder="请输入编号" name="no">
                                        </div>
                                        <div class="form-group input-group">
                                        	<input type="hidden" name="positionIds" id="deptFormPositionIds">
                                            <input type="text" class="form-control" placeholder="请选择职位" id="deptFormPositionNames">
                                            <span class="input-group-btn">
                                                <button class="btn btn-default" type="button" onclick="selectDeptPositions();"><i class="fa fa-search"></i>
                                                </button>
                                            </span>
                                        </div>
                                        <button type="button" onclick="submitDeptCreateForm ();" class="btn btn-default">提交</button>
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
                	function submitDeptCreateForm () {
                		$.post ('${pageContext.request.contextPath}/dept/createchild', $('#deptCreateForm').serializeArray(), function (result) {
                			if (result.success) {
                				$('#page-wrapper').load ('${pageContext.request.contextPath}/dept');
                			} else {
                				alert (result.message);
                			}
                		});
                	}
                	
                	function selectDeptPositions () {
                		$('#page-wrapper').hide().attr('id', "page-wrapper2");
                		$('#wrapper').append ('<div id="page-wrapper">职位列表选择</div>');
                		$('#page-wrapper').load ('${pageContext.request.contextPath}/dept/positions');
                	}
                </script>
            </div>
