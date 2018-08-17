<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            	修改部门
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form role="form" id="deptUpdateForm">
                                    	<div class="form-group">
                                            <label>ID</label>
                                            <input class="form-control"  name="id" value="${dept.id}" >
                                        </div>
                                        <div class="form-group">
                                            <label>名称</label>
                                            <input class="form-control"  name="name" value="${dept.name}" >
                                        </div>
                                       	<div class="form-group">
                                            <label>描述</label>
                                            <input class="form-control"  name="description" value="${dept.description}">
                                        </div>
                                        <div class="form-group">
                                            <label>上级</label>
                                            <input class="form-control"  name="description" value="${dept.parentName}">
                                        </div>
                                         <div class="form-group">
                                            <label>编号</label>
                                            <input class="form-control"  name="no" value="${dept.no}">
                                        </div>
                                        <div class="form-group input-group">
                                        	<input type="hidden" name="positionIds" id="deptFormPositionIds" value="${dept.positionIds}">
                                            <input type="text" class="form-control" value="${dept.positionNames}" id="deptFormPositionNames">
                                            <span class="input-group-btn">
                                                <button class="btn btn-default" type="button" onclick="selectDeptPositions();"><i class="fa fa-search"></i>
                                                </button>
                                            </span>
                                        </div>
                                        <button type="button" onclick="submitDeptUpdateForm ();" class="btn btn-default">提交</button>
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
                	function submitDeptUpdateForm () {
                		$.post ('${pageContext.request.contextPath}/dept/update', $('#deptUpdateForm').serializeArray(), function (result) {
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
