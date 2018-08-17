<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            	创建职位
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form role="form" id="positionCreateForm">
                                        <div class="form-group">
                                            <label>名称</label>
                                            <input class="form-control" placeholder="请输入名称" name="name">
                                        </div>
                                       	<div class="form-group">
                                            <label>描述</label>
                                            <input class="form-control" placeholder="请输入描述" name="description">
                                        </div>
                                        <div class="form-group">
                                            <label>级别</label>
                                            <input class="form-control" placeholder="请输入级别" name="level">
                                        </div>
                                        <button type="button" onclick="submitPositionCreateForm ();" class="btn btn-default">提交</button>
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
                	function submitPositionCreateForm () {
                		$.post ('${pageContext.request.contextPath}/position/create', $('#positionCreateForm').serializeArray(), function (result) {
                			if (result.success) {
                				$('#page-wrapper').load ('${pageContext.request.contextPath}/position');
                			} else {
                				alert (result.message);
                			}
                		});
                	}
                	
                </script>
            </div>
