(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[4],{bmMU:function(e,t){var n="undefined"!==typeof Element,r="function"===typeof Map,a="function"===typeof Set,o="function"===typeof ArrayBuffer&&!!ArrayBuffer.isView;function i(e,t){if(e===t)return!0;if(e&&t&&"object"==typeof e&&"object"==typeof t){if(e.constructor!==t.constructor)return!1;var u,c,s,l;if(Array.isArray(e)){if(u=e.length,u!=t.length)return!1;for(c=u;0!==c--;)if(!i(e[c],t[c]))return!1;return!0}if(r&&e instanceof Map&&t instanceof Map){if(e.size!==t.size)return!1;l=e.entries();while(!(c=l.next()).done)if(!t.has(c.value[0]))return!1;l=e.entries();while(!(c=l.next()).done)if(!i(c.value[1],t.get(c.value[0])))return!1;return!0}if(a&&e instanceof Set&&t instanceof Set){if(e.size!==t.size)return!1;l=e.entries();while(!(c=l.next()).done)if(!t.has(c.value[0]))return!1;return!0}if(o&&ArrayBuffer.isView(e)&&ArrayBuffer.isView(t)){if(u=e.length,u!=t.length)return!1;for(c=u;0!==c--;)if(e[c]!==t[c])return!1;return!0}if(e.constructor===RegExp)return e.source===t.source&&e.flags===t.flags;if(e.valueOf!==Object.prototype.valueOf)return e.valueOf()===t.valueOf();if(e.toString!==Object.prototype.toString)return e.toString()===t.toString();if(s=Object.keys(e),u=s.length,u!==Object.keys(t).length)return!1;for(c=u;0!==c--;)if(!Object.prototype.hasOwnProperty.call(t,s[c]))return!1;if(n&&e instanceof Element)return!1;for(c=u;0!==c--;)if(("_owner"!==s[c]&&"__v"!==s[c]&&"__o"!==s[c]||!e.$$typeof)&&!i(e[s[c]],t[s[c]]))return!1;return!0}return e!==e&&t!==t}e.exports=function(e,t){try{return i(e,t)}catch(n){if((n.message||"").match(/stack|recursion/i))return console.warn("react-fast-compare cannot handle circular refs"),!1;throw n}}},obeJ:function(e,t,n){"use strict";n.r(t);var r=n("VTBJ"),a=n("Hx5s"),o=n("qhky"),i=n("9kvl"),u=n("55Ip"),c=n("q1tI"),s=n.n(c),l=n("trCS"),f=n("mxmt"),p=n.n(f),d=n("roml"),T=n.n(d),y=function(e){var t=Object(i["i"])(),n=e.route,c=void 0===n?{routes:[]}:n,f=c.routes,d=void 0===f?[]:f,y=e.children,h=e.location,m=void 0===h?{pathname:""}:h,b=Object(a["f"])(d),E=b.breadcrumb,g=Object(a["g"])(Object(r["a"])({pathname:m.pathname,breadcrumb:E,formatMessage:t.formatMessage},e));return s.a.createElement(s.a.Fragment,null,s.a.createElement(o["a"],null,s.a.createElement("title",null,g),s.a.createElement("meta",{name:"description",content:g})),s.a.createElement("div",{className:T.a.container},s.a.createElement("div",{className:T.a.lang},s.a.createElement(l["a"],null)),s.a.createElement("div",{className:T.a.content},s.a.createElement("div",{className:T.a.top},s.a.createElement("div",{className:T.a.header},s.a.createElement(u["a"],{to:"/"},s.a.createElement("img",{alt:"logo",className:T.a.logo,src:p.a}),s.a.createElement("span",{className:T.a.title},"Ant Design"))),s.a.createElement("div",{className:T.a.desc},"Ant Design \u662f\u897f\u6e56\u533a\u6700\u5177\u5f71\u54cd\u529b\u7684 Web \u8bbe\u8ba1\u89c4\u8303")),y),s.a.createElement(a["a"],{copyright:"2020 \u901a\u7528\u7ba1\u7406\u7cfb\u7edf",links:[]})))};t["default"]=y},qhky:function(e,t,n){"use strict";(function(e){n.d(t,"a",(function(){return ie}));var r=n("17x9"),a=n.n(r),o=n("8+s/"),i=n.n(o),u=n("bmMU"),c=n.n(u),s=n("q1tI"),l=n.n(s),f=n("MgzW"),p=n.n(f),d={BODY:"bodyAttributes",HTML:"htmlAttributes",TITLE:"titleAttributes"},T={BASE:"base",BODY:"body",HEAD:"head",HTML:"html",LINK:"link",META:"meta",NOSCRIPT:"noscript",SCRIPT:"script",STYLE:"style",TITLE:"title"},y=(Object.keys(T).map((function(e){return T[e]})),{CHARSET:"charset",CSS_TEXT:"cssText",HREF:"href",HTTPEQUIV:"http-equiv",INNER_HTML:"innerHTML",ITEM_PROP:"itemprop",NAME:"name",PROPERTY:"property",REL:"rel",SRC:"src",TARGET:"target"}),h={accesskey:"accessKey",charset:"charSet",class:"className",contenteditable:"contentEditable",contextmenu:"contextMenu","http-equiv":"httpEquiv",itemprop:"itemProp",tabindex:"tabIndex"},m={DEFAULT_TITLE:"defaultTitle",DEFER:"defer",ENCODE_SPECIAL_CHARACTERS:"encodeSpecialCharacters",ON_CHANGE_CLIENT_STATE:"onChangeClientState",TITLE_TEMPLATE:"titleTemplate"},b=Object.keys(h).reduce((function(e,t){return e[h[t]]=t,e}),{}),E=[T.NOSCRIPT,T.SCRIPT,T.STYLE],g="data-react-helmet",v="function"===typeof Symbol&&"symbol"===typeof Symbol.iterator?function(e){return typeof e}:function(e){return e&&"function"===typeof Symbol&&e.constructor===Symbol&&e!==Symbol.prototype?"symbol":typeof e},A=function(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")},C=function(){function e(e,t){for(var n=0;n<t.length;n++){var r=t[n];r.enumerable=r.enumerable||!1,r.configurable=!0,"value"in r&&(r.writable=!0),Object.defineProperty(e,r.key,r)}}return function(t,n,r){return n&&e(t.prototype,n),r&&e(t,r),t}}(),O=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var n=arguments[t];for(var r in n)Object.prototype.hasOwnProperty.call(n,r)&&(e[r]=n[r])}return e},S=function(e,t){if("function"!==typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)},w=function(e,t){var n={};for(var r in e)t.indexOf(r)>=0||Object.prototype.hasOwnProperty.call(e,r)&&(n[r]=e[r]);return n},L=function(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!==typeof t&&"function"!==typeof t?e:t},I=function(e){var t=!(arguments.length>1&&void 0!==arguments[1])||arguments[1];return!1===t?String(e):String(e).replace(/&/g,"&amp;").replace(/</g,"&lt;").replace(/>/g,"&gt;").replace(/"/g,"&quot;").replace(/'/g,"&#x27;")},P=function(e){var t=k(e,T.TITLE),n=k(e,m.TITLE_TEMPLATE);if(n&&t)return n.replace(/%s/g,(function(){return Array.isArray(t)?t.join(""):t}));var r=k(e,m.DEFAULT_TITLE);return t||r||void 0},R=function(e){return k(e,m.ON_CHANGE_CLIENT_STATE)||function(){}},N=function(e,t){return t.filter((function(t){return"undefined"!==typeof t[e]})).map((function(t){return t[e]})).reduce((function(e,t){return O({},e,t)}),{})},j=function(e,t){return t.filter((function(e){return"undefined"!==typeof e[T.BASE]})).map((function(e){return e[T.BASE]})).reverse().reduce((function(t,n){if(!t.length)for(var r=Object.keys(n),a=0;a<r.length;a++){var o=r[a],i=o.toLowerCase();if(-1!==e.indexOf(i)&&n[i])return t.concat(n)}return t}),[])},M=function(e,t,n){var r={};return n.filter((function(t){return!!Array.isArray(t[e])||("undefined"!==typeof t[e]&&F("Helmet: "+e+' should be of type "Array". Instead found type "'+v(t[e])+'"'),!1)})).map((function(t){return t[e]})).reverse().reduce((function(e,n){var a={};n.filter((function(e){for(var n=void 0,o=Object.keys(e),i=0;i<o.length;i++){var u=o[i],c=u.toLowerCase();-1===t.indexOf(c)||n===y.REL&&"canonical"===e[n].toLowerCase()||c===y.REL&&"stylesheet"===e[c].toLowerCase()||(n=c),-1===t.indexOf(u)||u!==y.INNER_HTML&&u!==y.CSS_TEXT&&u!==y.ITEM_PROP||(n=u)}if(!n||!e[n])return!1;var s=e[n].toLowerCase();return r[n]||(r[n]={}),a[n]||(a[n]={}),!r[n][s]&&(a[n][s]=!0,!0)})).reverse().forEach((function(t){return e.push(t)}));for(var o=Object.keys(a),i=0;i<o.length;i++){var u=o[i],c=p()({},r[u],a[u]);r[u]=c}return e}),[]).reverse()},k=function(e,t){for(var n=e.length-1;n>=0;n--){var r=e[n];if(r.hasOwnProperty(t))return r[t]}return null},_=function(e){return{baseTag:j([y.HREF,y.TARGET],e),bodyAttributes:N(d.BODY,e),defer:k(e,m.DEFER),encode:k(e,m.ENCODE_SPECIAL_CHARACTERS),htmlAttributes:N(d.HTML,e),linkTags:M(T.LINK,[y.REL,y.HREF],e),metaTags:M(T.META,[y.NAME,y.CHARSET,y.HTTPEQUIV,y.PROPERTY,y.ITEM_PROP],e),noscriptTags:M(T.NOSCRIPT,[y.INNER_HTML],e),onChangeClientState:R(e),scriptTags:M(T.SCRIPT,[y.SRC,y.INNER_HTML],e),styleTags:M(T.STYLE,[y.CSS_TEXT],e),title:P(e),titleAttributes:N(d.TITLE,e)}},H=function(){var e=Date.now();return function(t){var n=Date.now();n-e>16?(e=n,t(n)):setTimeout((function(){H(t)}),0)}}(),x=function(e){return clearTimeout(e)},D="undefined"!==typeof window?window.requestAnimationFrame&&window.requestAnimationFrame.bind(window)||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||H:e.requestAnimationFrame||H,B="undefined"!==typeof window?window.cancelAnimationFrame||window.webkitCancelAnimationFrame||window.mozCancelAnimationFrame||x:e.cancelAnimationFrame||x,F=function(e){return console&&"function"===typeof console.warn&&console.warn(e)},q=null,Y=function(e){q&&B(q),e.defer?q=D((function(){U(e,(function(){q=null}))})):(U(e),q=null)},U=function(e,t){var n=e.baseTag,r=e.bodyAttributes,a=e.htmlAttributes,o=e.linkTags,i=e.metaTags,u=e.noscriptTags,c=e.onChangeClientState,s=e.scriptTags,l=e.styleTags,f=e.title,p=e.titleAttributes;V(T.BODY,r),V(T.HTML,a),K(f,p);var d={baseTag:X(T.BASE,n),linkTags:X(T.LINK,o),metaTags:X(T.META,i),noscriptTags:X(T.NOSCRIPT,u),scriptTags:X(T.SCRIPT,s),styleTags:X(T.STYLE,l)},y={},h={};Object.keys(d).forEach((function(e){var t=d[e],n=t.newTags,r=t.oldTags;n.length&&(y[e]=n),r.length&&(h[e]=d[e].oldTags)})),t&&t(),c(e,y,h)},z=function(e){return Array.isArray(e)?e.join(""):e},K=function(e,t){"undefined"!==typeof e&&document.title!==e&&(document.title=z(e)),V(T.TITLE,t)},V=function(e,t){var n=document.getElementsByTagName(e)[0];if(n){for(var r=n.getAttribute(g),a=r?r.split(","):[],o=[].concat(a),i=Object.keys(t),u=0;u<i.length;u++){var c=i[u],s=t[c]||"";n.getAttribute(c)!==s&&n.setAttribute(c,s),-1===a.indexOf(c)&&a.push(c);var l=o.indexOf(c);-1!==l&&o.splice(l,1)}for(var f=o.length-1;f>=0;f--)n.removeAttribute(o[f]);a.length===o.length?n.removeAttribute(g):n.getAttribute(g)!==i.join(",")&&n.setAttribute(g,i.join(","))}},X=function(e,t){var n=document.head||document.querySelector(T.HEAD),r=n.querySelectorAll(e+"["+g+"]"),a=Array.prototype.slice.call(r),o=[],i=void 0;return t&&t.length&&t.forEach((function(t){var n=document.createElement(e);for(var r in t)if(t.hasOwnProperty(r))if(r===y.INNER_HTML)n.innerHTML=t.innerHTML;else if(r===y.CSS_TEXT)n.styleSheet?n.styleSheet.cssText=t.cssText:n.appendChild(document.createTextNode(t.cssText));else{var u="undefined"===typeof t[r]?"":t[r];n.setAttribute(r,u)}n.setAttribute(g,"true"),a.some((function(e,t){return i=t,n.isEqualNode(e)}))?a.splice(i,1):o.push(n)})),a.forEach((function(e){return e.parentNode.removeChild(e)})),o.forEach((function(e){return n.appendChild(e)})),{oldTags:a,newTags:o}},G=function(e){return Object.keys(e).reduce((function(t,n){var r="undefined"!==typeof e[n]?n+'="'+e[n]+'"':""+n;return t?t+" "+r:r}),"")},J=function(e,t,n,r){var a=G(n),o=z(t);return a?"<"+e+" "+g+'="true" '+a+">"+I(o,r)+"</"+e+">":"<"+e+" "+g+'="true">'+I(o,r)+"</"+e+">"},Q=function(e,t,n){return t.reduce((function(t,r){var a=Object.keys(r).filter((function(e){return!(e===y.INNER_HTML||e===y.CSS_TEXT)})).reduce((function(e,t){var a="undefined"===typeof r[t]?t:t+'="'+I(r[t],n)+'"';return e?e+" "+a:a}),""),o=r.innerHTML||r.cssText||"",i=-1===E.indexOf(e);return t+"<"+e+" "+g+'="true" '+a+(i?"/>":">"+o+"</"+e+">")}),"")},W=function(e){var t=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{};return Object.keys(e).reduce((function(t,n){return t[h[n]||n]=e[n],t}),t)},$=function(e){var t=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{};return Object.keys(e).reduce((function(t,n){return t[b[n]||n]=e[n],t}),t)},Z=function(e,t,n){var r,a=(r={key:t},r[g]=!0,r),o=W(n,a);return[l.a.createElement(T.TITLE,o,t)]},ee=function(e,t){return t.map((function(t,n){var r,a=(r={key:n},r[g]=!0,r);return Object.keys(t).forEach((function(e){var n=h[e]||e;if(n===y.INNER_HTML||n===y.CSS_TEXT){var r=t.innerHTML||t.cssText;a.dangerouslySetInnerHTML={__html:r}}else a[n]=t[e]})),l.a.createElement(e,a)}))},te=function(e,t,n){switch(e){case T.TITLE:return{toComponent:function(){return Z(e,t.title,t.titleAttributes,n)},toString:function(){return J(e,t.title,t.titleAttributes,n)}};case d.BODY:case d.HTML:return{toComponent:function(){return W(t)},toString:function(){return G(t)}};default:return{toComponent:function(){return ee(e,t)},toString:function(){return Q(e,t,n)}}}},ne=function(e){var t=e.baseTag,n=e.bodyAttributes,r=e.encode,a=e.htmlAttributes,o=e.linkTags,i=e.metaTags,u=e.noscriptTags,c=e.scriptTags,s=e.styleTags,l=e.title,f=void 0===l?"":l,p=e.titleAttributes;return{base:te(T.BASE,t,r),bodyAttributes:te(d.BODY,n,r),htmlAttributes:te(d.HTML,a,r),link:te(T.LINK,o,r),meta:te(T.META,i,r),noscript:te(T.NOSCRIPT,u,r),script:te(T.SCRIPT,c,r),style:te(T.STYLE,s,r),title:te(T.TITLE,{title:f,titleAttributes:p},r)}},re=function(e){var t,n;return n=t=function(t){function n(){return A(this,n),L(this,t.apply(this,arguments))}return S(n,t),n.prototype.shouldComponentUpdate=function(e){return!c()(this.props,e)},n.prototype.mapNestedChildrenToProps=function(e,t){if(!t)return null;switch(e.type){case T.SCRIPT:case T.NOSCRIPT:return{innerHTML:t};case T.STYLE:return{cssText:t}}throw new Error("<"+e.type+" /> elements are self-closing and can not contain children. Refer to our API for more information.")},n.prototype.flattenArrayTypeChildren=function(e){var t,n=e.child,r=e.arrayTypeChildren,a=e.newChildProps,o=e.nestedChildren;return O({},r,(t={},t[n.type]=[].concat(r[n.type]||[],[O({},a,this.mapNestedChildrenToProps(n,o))]),t))},n.prototype.mapObjectTypeChildren=function(e){var t,n,r=e.child,a=e.newProps,o=e.newChildProps,i=e.nestedChildren;switch(r.type){case T.TITLE:return O({},a,(t={},t[r.type]=i,t.titleAttributes=O({},o),t));case T.BODY:return O({},a,{bodyAttributes:O({},o)});case T.HTML:return O({},a,{htmlAttributes:O({},o)})}return O({},a,(n={},n[r.type]=O({},o),n))},n.prototype.mapArrayTypeChildrenToProps=function(e,t){var n=O({},t);return Object.keys(e).forEach((function(t){var r;n=O({},n,(r={},r[t]=e[t],r))})),n},n.prototype.warnOnInvalidChildren=function(e,t){return!0},n.prototype.mapChildrenToProps=function(e,t){var n=this,r={};return l.a.Children.forEach(e,(function(e){if(e&&e.props){var a=e.props,o=a.children,i=w(a,["children"]),u=$(i);switch(n.warnOnInvalidChildren(e,o),e.type){case T.LINK:case T.META:case T.NOSCRIPT:case T.SCRIPT:case T.STYLE:r=n.flattenArrayTypeChildren({child:e,arrayTypeChildren:r,newChildProps:u,nestedChildren:o});break;default:t=n.mapObjectTypeChildren({child:e,newProps:t,newChildProps:u,nestedChildren:o});break}}})),t=this.mapArrayTypeChildrenToProps(r,t),t},n.prototype.render=function(){var t=this.props,n=t.children,r=w(t,["children"]),a=O({},r);return n&&(a=this.mapChildrenToProps(n,a)),l.a.createElement(e,a)},C(n,null,[{key:"canUseDOM",set:function(t){e.canUseDOM=t}}]),n}(l.a.Component),t.propTypes={base:a.a.object,bodyAttributes:a.a.object,children:a.a.oneOfType([a.a.arrayOf(a.a.node),a.a.node]),defaultTitle:a.a.string,defer:a.a.bool,encodeSpecialCharacters:a.a.bool,htmlAttributes:a.a.object,link:a.a.arrayOf(a.a.object),meta:a.a.arrayOf(a.a.object),noscript:a.a.arrayOf(a.a.object),onChangeClientState:a.a.func,script:a.a.arrayOf(a.a.object),style:a.a.arrayOf(a.a.object),title:a.a.string,titleAttributes:a.a.object,titleTemplate:a.a.string},t.defaultProps={defer:!0,encodeSpecialCharacters:!0},t.peek=e.peek,t.rewind=function(){var t=e.rewind();return t||(t=ne({baseTag:[],bodyAttributes:{},encodeSpecialCharacters:!0,htmlAttributes:{},linkTags:[],metaTags:[],noscriptTags:[],scriptTags:[],styleTags:[],title:"",titleAttributes:{}})),t},n},ae=function(){return null},oe=i()(_,Y,ne)(ae),ie=re(oe);ie.renderStatic=ie.rewind}).call(this,n("yLpj"))},roml:function(e,t,n){e.exports={container:"antd-pro-layouts-user-layout-container",lang:"antd-pro-layouts-user-layout-lang",content:"antd-pro-layouts-user-layout-content",top:"antd-pro-layouts-user-layout-top",header:"antd-pro-layouts-user-layout-header",logo:"antd-pro-layouts-user-layout-logo",title:"antd-pro-layouts-user-layout-title",desc:"antd-pro-layouts-user-layout-desc"}}}]);