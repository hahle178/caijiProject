/*
 * jQuery pager plugin
 * Version 1.0 (12/22/2008)
 * @requires jQuery v1.2.6 or later
 *
 * Example at: http://jonpauldavies.github.com/JQuery/Pager/PagerDemo.html
 *
 * Copyright (c) 2008-2009 Jon Paul Davies
 * Dual licensed under the MIT and GPL licenses:
 * http://www.opensource.org/licenses/mit-license.php
 * http://www.gnu.org/licenses/gpl.html
 *
 * Read the related blog post and contact the author at http://www.j-dee.com/2008/12/22/jquery-pager-plugin/
 *
 * This version is far from perfect and doesn't manage it's own state, therefore contributions are more than welcome!
 *
 * Usage: .pager({ pagenumber: 1, pagecount: 15, buttonClickCallback: PagerClickTest });
 *
 * Where pagenumber is the visible page number
 *       pagecount is the total number of pages to display
 *       buttonClickCallback is the method to fire when a pager button is clicked.
 *
 * buttonClickCallback signiture is PagerClickTest = function(pageclickednumber)
 * Where pageclickednumber is the number of the page clicked in the control.
 *
 * The included Pager.CSS file is a dependancy but can obviously tweaked to your wishes
 * Tested in IE6 IE7 Firefox & Safari. Any browser strangeness, please report.
 */
/**
 * @Modify by wangchen 2016-12-12
 * 增加id=currentPageNumber 隐藏域，仅用于标识当前页数，js有特殊需求要获取当前页数可以获取该隐藏域的值
 * @Modify by wangchen 2017-2-14 增加了分页可通过参数传入每页显示条数，配置options:{pagesizes:[10,20,30]}
 */
(function($) {

    $.fn.pager = function(options) {
        var opts = $.extend({}, $.fn.pager.defaults, options);

        return this.each(function() {

            // empty out the destination element and then render out the pager with the supplied options
            var thisid = $(this).attr("id");
//            $(this).empty().append(renderpager(parseInt(options.pagenumber), parseInt(options.pagecount), parseInt(options.rowcount),parseInt(options.pagesize),options.buttonClickCallback));
            $(this).replaceWith(renderpager(parseInt(options.pagenumber), parseInt(options.pagecount), parseInt(options.rowcount),parseInt(options.pagesize),options.buttonClickCallback,thisid,options.pagesizes));
            pagesizeconfig(options.pagesize,options.buttonClickCallback,thisid);
            // specify correct cursor activity
//            $('.pages a').mouseover(function() { document.body.style.cursor = "pointer"; }).mouseout(function() { document.body.style.cursor = "auto"; });

        });
    };
    // render and return the pager with the supplied options
    function renderpager(pagenumber, pagecount,rowcount,pagesize, buttonClickCallback, thisid,pagesizes) {
        if(!rowcount){
            rowcount=0;
        }
        if(!pagecount){
            pagecount=0;
        }
        // setup $pager to hold render
        var $pager = $('<div class="row" id="' + thisid + '"></div>');

        // add totle page info
        $pager.append('<div class="col-sm-5"><div class="dataTables_info" role="status" aria-live="polite">共'+rowcount+'条，'+pagecount+'页</div> </div>');


        var $column= $('<div class="col-sm-7"></div>');

        var $container= $('<div class="dataTables_paginate paging_simple_numbers"><input type="hidden" id="' + thisid + '_page" name="pageNumber" value="1"><input type="hidden" id="currentPageNumber" name="currentPageNumber" value="'+pagenumber+'"></div>');

        var $pagination = $('<ul class="pagination"></ul>');
        //$pager.append( "<input type='hidden' id='pagetemp' value='"+pagenumber+"'>");
        // add in the previous and next buttons
        $pagination.append(renderButton('首页', pagenumber, pagecount, buttonClickCallback, thisid)).append(renderButton('&lt;', pagenumber, pagecount, buttonClickCallback, thisid));


        // pager currently only handles 10 viewable pages ( could be easily parameterized, maybe in next version ) so handle edge cases
        var startPoint = 1;
        var endPoint = 7;

        if (pagenumber > 3) {
            startPoint = pagenumber - 3;
            endPoint = pagenumber + 3;
        }

        if (endPoint > pagecount) {
            startPoint = pagecount - 6;
            endPoint = pagecount;
        }

        if (startPoint < 1) {
            startPoint = 1;
        }

        // loop thru visible pages and render buttons
        for (var page = startPoint; page <= endPoint; page++) {

            var currentButton = $('<li class="paginate_button"><a href="javascript:void(0)" aria-controls="example2" data-dt-idx="0" tabindex="0">' + (page) + '</a> </li>');

//            page == pagenumber ? currentButton.addClass('ps') : currentButton.click(function(event) {buttonClickCallback( $(event.target).html());});
            page == pagenumber ? currentButton.addClass('active') : currentButton.click(function(event) {
                $('#' + thisid + ' #' + thisid + '_page').val($(event.target).html());
                buttonClickCallback();

            });
            currentButton.appendTo($pagination);
        }

        // render in the next and 尾页 buttons before returning the whole rendered control back.
        $pagination.append(renderButton('&gt;', pagenumber, pagecount, buttonClickCallback, thisid)).append(renderButton('尾页', pagenumber, pagecount, buttonClickCallback, thisid));

        //add page size  pagesizes
        /*$pagination.append("<li>&nbsp;每页显示：</li><li><select id='" + thisid + "_pageSize' name='pageSize' class='form-control'>");
         $pagination.append("<option value='10'>10</option><option value='20'>20</option><option value='30'>30</option>");
         $pagination.append("</select></li>");*/
        var pSize = $('input[name="pageSize"]');
        //console.log('pSize.length===='+pSize.length);
        if(pSize.length>0){//检查是否存在已有的name=pageSize对象，如果存在则删除掉
            pSize.remove();
        }
        var pagesizesArr = [];
        pagesizesArr.push("<li>&nbsp;每页显示：</li><li><select id='" + thisid + "_pageSize' name='pageSize' class='form-control'>");
        if(typeof(pagesizes)!='undefined' && pagesizes.length >0){
            for(var i=0;i<pagesizes.length;i++){
                pagesizesArr.push("<option value='");
                pagesizesArr.push(pagesizes[i]);
                pagesizesArr.push("'>");
                pagesizesArr.push(pagesizes[i]);
                pagesizesArr.push("</option>");
            }
        }else{
            pagesizesArr.push("<option value='10'>10</option><option value='20'>20</option><option value='30'>30</option>");
        }
        pagesizesArr.push("</select></li>");
        $pagination.append(pagesizesArr.join(''));
        $container.append($pagination);
        $column.append($container);
        $pager.append($column);

        return $pager;
    }

    // renders and returns a 'specialized' button, ie 'next', 'previous' etc. rather than a page number button
    function renderButton(buttonLabel, pagenumber, pagecount, buttonClickCallback, thisid) {

        var $Button = $('<li class="paginate_button"><a href="javascript:void(0)" aria-controls="example2" data-dt-idx="0" tabindex="0">' + buttonLabel + '</a> </li>');

        var destPage = 1;

        // work out destination page for required button type
        switch (buttonLabel) {
            case "首页":
                destPage = 1;
                break;
            case "&lt;":
                destPage = pagenumber - 1;
                break;
            case "&gt;":
                destPage = pagenumber + 1;
                break;
            case "尾页":
                destPage = pagecount;
                break;
        }

        // disable and 'grey' out buttons if not needed.
        if (buttonLabel == "首页" || buttonLabel == "&lt;") {
            pagenumber <= 1 ? $Button.addClass('empty') : $Button.click(function() {
                $('#' + thisid + ' #' + thisid + '_page').val(destPage);
                buttonClickCallback();

            });
        }
        else {
            pagenumber >= pagecount ? $Button.addClass('empty') : $Button.click(function() {
                $('#' + thisid + ' #' + thisid + '_page').val(destPage);
                buttonClickCallback();

            });
        }

        return $Button;
    }
    function pagesizeconfig(pagesize,buttonClickCallback,thisid){
        $('#' + thisid + ' #' + thisid + '_pageSize').change(function(){
            buttonClickCallback();
        });
        $("#" + thisid + " #" + thisid + "_pageSize option[value='"+pagesize+"']").attr("selected",true);

    }

    // pager defaults. hardly worth bothering with in this case but used as placeholder for expansion in the next version
    $.fn.pager.defaults = {
        pagenumber: 1,
        pagecount: 1
    };

})(jQuery);
