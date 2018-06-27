window.org_strangeway_responsive_web_components_impl_cssextension_CustomCssAttributesExtension = function() {
    var self = this;
    var target = self.getElement(self.getParentId());

    self.onStateChange = function () {
        var css = this.getState().css;

        for (var property in css) {
            if (css.hasOwnProperty(property)) {
                target.style[property] = css[property];
            }
        }
    }
};