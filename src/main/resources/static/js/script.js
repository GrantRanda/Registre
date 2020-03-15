/**
 * Enables accordion list behavior for sidebar menus by hiding all other
 * collapsible menus when one is toggled.
 */
const $group = $('#navContent');
$group.on('show.bs.collapse', '.collapse', function () {
    $group.find('.collapse.show').collapse('hide');
});

/**
 * Enables tooltips.
 */
$(document).ready(function () {
    $('[data-toggle="tooltip"]').tooltip();
});

/**
 *  Hides the URLs from sidebar dropdown menus so that they do not display in
 *  the browser's status bar.
 */
$(function () {
    $("a.sidebar-menu").each(function (index, element) {
        const href = $(this).attr("href");
        $(this).attr("hrefHidden", href);
        $(this).removeAttr("href");
    });
});

/**
 * Refreshes a page to display the selected number of entries for a table.
 */
$(function () {
    $("#entriesSelect").change(function () {
        let url = location.protocol + "//" + location.host + location.pathname;
        if (url.endsWith("/")) {
            url = url.slice(0, -1);
        }
        self.location = url + '?size=' + this.options[this.selectedIndex].value + '&page=1';
    })
});