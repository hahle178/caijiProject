/**
 * Created by zhaofs on 2016/7/19.
 * --------------------
 * - 布局 -
 * --------------------
 * 主要实现整体布局的控制，初始化等，如菜单，通知，退出等功能。
 */
import SidebarMenu from "../../../../src/layout/sidebar/SidebarMenu";

export default class extends SidebarMenu {

    /**
     * 初始化菜单
     */
    initSidebarMenu() {
        this.menuContainer = $(`#${this.menuId}`);
        return new Promise((resolve, reject) => {
            let htmlStr = store.get('menuStr');
            if (htmlStr) {
                this.renderMenu(htmlStr);
                resolve({});
            } else {
                htmlStr = this.getMenuTpl()({});
                store.set('menuStr', htmlStr);
                this.renderMenu(htmlStr);
                resolve({});
            }
        });
    }

    /**
     * 获取菜单模板
     * @returns {*}
     */
    getMenuTpl() {
        return require("./menu.html");
    }

};