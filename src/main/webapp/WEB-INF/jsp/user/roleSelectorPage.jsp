<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

			<div class="row">
                <div class="col-lg-12">
                    <h4>选择角色</h4>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="roleTable">
                                <thead>
                                    <tr>
                                      <th>ID</th>
                                       <th>角色名</th>
                                       <th>角色描述</th>
                                       <th>操作权限</th>
                                    </tr>
                                </thead>
                            </table>
                            <!-- /.table-responsive -->
                           
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <script>
            // 用于存放用户点击菜单时的临时id和名称，当用户点击确定时，该值才覆盖userRoleIds和userRoleNames的值
            var selectedRole = {
            	ids : $('#userFormRoleIds').val(),
            	names : $('#userFormRoleNames').val()
            };
            console.log (selectedRole);
            $(document).ready(function() {
            	var tableId = 'roleTable';
            	// 初始化列表，并触发后台的json数据获取
                var table = $('#' + tableId).DataTable({
                	"processing": true,
                    "serverSide": true,
                    "rowId" : 'id',
                    "ajax": {
                        "url": "${pageContext.request.contextPath}/user/role/list",
                        "type": "GET"
                    },
                    "order": [[ 1, "asc" ]] ,
                    "columns": [
                    	{ "data": "id" , "orderable" : false},
                    	{ "data": "name" },
                        { "data": "description" , "orderable" : false},
                        { "data": "menuNames" , "orderable" : false},
                       
                    ],
                    // 创建行之后的回调方法
                    "createdRow" : function( row, data, dataIndex ) {
                    	// 获取菜单的ids
                    	var ids = selectedRole.ids.split(',');
                    	for (var i = 0; i < ids.length; i ++) {
                    		if (data.id == ids[i]) {
                    			$(row).addClass( 'info' );
                    		}
                    	}
                    }
                });
             	// 设置列表多选
                $('#' + tableId + ' tbody').on( 'click', 'tr', function () {
                	var r = table.row( this ).data();
                	console.log (r);
                	// 如果有info样式则删除，没有则添加
                	if ($(this).attr ('class').indexOf ('info') > -1) {
                		// 删除
                		var ids = selectedRole.ids.split(',');
                		var names = selectedRole.names.split(',');
                		selectedRole.ids = '';
                		selectedRole.names = '';
                		for (var i = 0; i < ids.length; i ++) {
                			if (ids[i] != r.id) {
                				if (selectedRole.ids != '') {
                					selectedRole.ids += ',';
                    				selectedRole.names += ',';
                				}
                				selectedRole.ids += ids[i];
                				selectedRole.names += names[i];
                			}
                		}
                		console.log ('after delete :' + selectedRole.names);
                	} else {
                		// 添加
                		if (selectedRole.ids != '') {
           					selectedRole.ids += ',';
               				selectedRole.names += ',';
           				}
                		selectedRole.ids += r.id;
        				selectedRole.names += r.name;
        				console.log ('after add :' + selectedRole.names);
                	}
                	$(this).toggleClass('info');
                } );
                // 添加工具栏按钮，找到设置分页的div，并添加创建和批量删除按钮的a标签
                $('#' + tableId + '_length').append (" <a class='btn btn-primary btn-sm' onclick='selectRoleOK();'>确定</a> <a class='btn btn-primary btn-warning btn-sm' onclick='selectRoleCancel();'>取消</a>");
            });
            
            function selectRoleOK () {
            	var table = $('#RoleTable').DataTable ();
            	$('#userFormRoleIds').val (selectedRole.ids);
            	$('#userFormRoleNames').val (selectedRole.names);
            	$('#page-wrapper').remove();
            	$('#page-wrapper2').show().attr('id', 'page-wrapper');
            }
            
            function selectRoleCancel () {
            	$('#page-wrapper').remove();
            	$('#page-wrapper2').show().attr('id', 'page-wrapper');
		    }
		    </script>