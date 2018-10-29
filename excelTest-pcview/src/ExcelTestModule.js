/**
* Created by auto on 2017/5/30.
* 导入模块
*/
import Module from "ezdev-pcview/base/Module";

new Module({
name: "exceltest",

/**
* 动态导入Handler
* @param path
* @returns {*}
*/
dynamicImportHandler: function (path) {
    try {
        return import(`./business/${path}/Handler.js`);
    } catch (err) {
        console.error("load handler error");
    }
}
});
