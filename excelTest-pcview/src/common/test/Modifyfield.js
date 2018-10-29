/**
*dsfdsfdsfdsfds
 */
let html = null;
let count = 0;
export default {
    addfield: function () {
        count++;
         html = '<div class="box-body">\n' +
             '                            <!-- hidden input -->\n' +
             '                            <input type="hidden" name="versionNum"  class="form-control" >\n' +
             '                            <!-- text input -->\n' +
             '                            <div class="col-md-3">\n' +
             '                                <div class="form-group">\n' +
             '                                    <label><i class="fa"></i>&nbsp;字段名称</label>\n' +
             '                                    <input type="text" name="fieldName"  class="form-control"  datatype="*" placeholder="请输入字段名称" />\n' +
             '                                    <span class="help-block"></span>\n' +
             '                                </div>\n' +
             '                            </div>\n' +
             '                            <div class="col-md-3">\n' +
             '                                <div class="form-group">\n' +
             '                                    <label><i class="fa"></i>&nbsp;字段备注</label>\n' +
             '                                    <input type="text" name="fieldRemark"  class="form-control" datatype="*" placeholder="请输入字段备注" />\n' +
             '                                    <span class="help-block"></span>\n' +
             '                                </div>\n' +
             '                             </div>\n' +
             '                            <div class="col-md-3">\n' +
             '                                <div class="form-group">\n' +
             '                                    <label><i class="fa"></i>&nbsp;字段值</label>\n' +
             '                                    <input type="text" name="fieldValue"  class="form-control" datatype="*" placeholder="请输入字段值" />\n' +
             '                                    <span class="help-block"></span>\n' +
             '                                </div>\n' +
             '                            </div>\n' +
             '                            <div class="col-md-1">\n' +
             '                                <div class="form-group">\n' +
             '                                    <label><i class="fa"></i>&nbsp;操作</label>\n' +
             '                                     <button e-event="href:/exceltest/exceltest/bcpmessage/delField"  id="node'+count+'"'+'  class="btn btn-primary form-control"  type="button" title="删除">\n' +
             '                                        <i class="fa fa-remove"></i>&nbsp;删除\n' +
             '                                    </button>\n' +
             '                                    <span class="help-block"></span>\n' +
             '                                </div>\n' +
             '                            </div>\n' +
             '                        </div>';
        return html;
    },

    delfield: function(node){
        alert("1111111")
       /* $(node).parent().remove();*/
    }
}
