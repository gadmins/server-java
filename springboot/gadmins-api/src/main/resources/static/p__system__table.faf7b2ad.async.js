(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[19],{N1TQ:function(e,t,a){"use strict";a.r(t);var n=a("VTBJ"),r=(a("qVdP"),a("jsC+")),c=(a("lUTK"),a("BvKs")),u=(a("2qtc"),a("kLXV")),l=(a("+L6B"),a("2/Rp")),s=(a("/zsF"),a("PArb")),i=a("ODXe"),o=a("o0o1"),m=a.n(o),p=(a("miYZ"),a("tsqr")),b=a("HaE+"),d=a("Hx5s"),f=a("q1tI"),h=a.n(f),v=a("Qiat"),w=a("9kvl"),E=a("sy1d"),O=a("xvlK"),y=a("8Skl"),k=a("6R3i"),j=a("Hwgs");function x(e){return C.apply(this,arguments)}function C(){return C=Object(b["a"])(m.a.mark((function e(t){return m.a.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.abrupt("return",Object(E["c"])("/adminapi/db/table",{params:t}));case 1:case"end":return e.stop()}}),e)}))),C.apply(this,arguments)}function T(e){return A.apply(this,arguments)}function A(){return A=Object(b["a"])(m.a.mark((function e(t){return m.a.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.abrupt("return",Object(E["c"])("/adminapi/db/table",{method:"POST",data:Object(n["a"])({},t)}));case 1:case"end":return e.stop()}}),e)}))),A.apply(this,arguments)}function N(e){return g.apply(this,arguments)}function g(){return g=Object(b["a"])(m.a.mark((function e(t){return m.a.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.abrupt("return",Object(E["c"])("/adminapi/db/table/".concat(t.join(",")),{method:"DELETE"}));case 1:case"end":return e.stop()}}),e)}))),g.apply(this,arguments)}function B(e){return I.apply(this,arguments)}function I(){return I=Object(b["a"])(m.a.mark((function e(t){return m.a.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.abrupt("return",Object(E["c"])("/adminapi/db/table/".concat(t.name),{method:"PUT",data:Object(n["a"])({},t)}));case 1:case"end":return e.stop()}}),e)}))),I.apply(this,arguments)}a("BoS7");var S=a("Sdc0"),V=a("wx14"),L=(a("5NDa"),a("5rEg")),M=(a("y8nQ"),a("Vl3Y")),F=M["a"].Item,_={labelCol:{span:5},wrapperCol:{span:18}},q=function(e){var t=e.modalVisible,a=e.onSubmit,n=e.onCancel,r=e.initVals,c=M["a"].useForm(),l=Object(i["a"])(c,1),s=l[0],o=r?{tableName:r.name,tableComment:r.comment,hasDelete:!1}:{hasDelete:!1},p=function(){var e=Object(b["a"])(m.a.mark((function e(){var t,n;return m.a.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.next=2,s.validateFields();case 2:return t=e.sent,e.next=5,a({name:t.tableName,comment:t.tableComment,hasDelete:t.hasDelete});case 5:n=e.sent,n&&s.resetFields();case 7:case"end":return e.stop()}}),e)})));return function(){return e.apply(this,arguments)}}();return h.a.createElement(u["a"],{maskClosable:!1,destroyOnClose:!0,visible:t,title:r?"\u590d\u5236\u8868":"\u521b\u5efa\u8868",onOk:p,onCancel:function(){s.resetFields(),n()}},h.a.createElement(M["a"],{form:s,initialValues:o},h.a.createElement(F,Object(V["a"])({},_,{label:"\u8868\u540d",name:"tableName",rules:[{required:!0,message:"\u8868\u540d\u4e0d\u80fd\u4e3a\u7a7a"}]}),h.a.createElement(L["a"],{placeholder:"\u8bf7\u8f93\u5165"})),h.a.createElement(F,Object(V["a"])({},_,{label:"\u8868\u6ce8\u91ca",name:"tableComment",rules:[{required:!0,message:"\u8868\u6ce8\u91ca\u4e0d\u80fd\u4e3a\u7a7a"}]}),h.a.createElement(L["a"],{placeholder:"\u8bf7\u8f93\u5165"})),h.a.createElement(F,Object(V["a"])({},_,{label:"\u903b\u8f91\u5220\u9664",name:"hasDelete"}),h.a.createElement(S["a"],null))))},R=M["a"].Item,D={labelCol:{span:5},wrapperCol:{span:18}},P=function(e){var t=e.modalVisible,a=e.onSubmit,n=e.onCancel,r=e.initVals,c=M["a"].useForm(),l=Object(i["a"])(c,1),s=l[0],o=r?{tableName:r.name,tableComment:r.comment}:{},p=function(){var e=Object(b["a"])(m.a.mark((function e(){var t,n;return m.a.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.next=2,s.validateFields();case 2:return t=e.sent,t.newName=t.name,t.name=r.tableName,e.next=7,a({name:r.tableName,newName:t.tableName,comment:t.tableComment});case 7:n=e.sent,n&&s.resetFields();case 9:case"end":return e.stop()}}),e)})));return function(){return e.apply(this,arguments)}}();return h.a.createElement(u["a"],{maskClosable:!1,destroyOnClose:!0,visible:t,title:"\u7f16\u8f91\u8868",onOk:p,onCancel:function(){s.resetFields(),n()}},h.a.createElement(M["a"],{form:s,initialValues:o},h.a.createElement(R,Object(V["a"])({},D,{label:"\u8868\u540d",name:"tableName",rules:[{required:!0,message:"\u8868\u540d\u4e0d\u80fd\u4e3a\u7a7a"}]}),h.a.createElement(L["a"],{placeholder:"\u8bf7\u8f93\u5165"})),h.a.createElement(R,Object(V["a"])({},D,{label:"\u8868\u6ce8\u91ca",name:"tableComment",rules:[{required:!0,message:"\u8868\u6ce8\u91ca\u4e0d\u80fd\u4e3a\u7a7a"}]}),h.a.createElement(L["a"],{placeholder:"\u8bf7\u8f93\u5165"}))))},K=function(){var e=Object(b["a"])(m.a.mark((function e(t){var a,n;return m.a.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return a=p["a"].loading("\u6b63\u5728\u6dfb\u52a0"),e.prev=1,e.next=4,T(t);case 4:if(n=e.sent,a(),!E["a"].isOk(n)){e.next=9;break}return p["a"].success("\u6dfb\u52a0\u6210\u529f"),e.abrupt("return",!0);case 9:return p["a"].warn(n.msg),e.abrupt("return",!1);case 13:return e.prev=13,e.t0=e["catch"](1),a(),p["a"].error("\u6dfb\u52a0\u5931\u8d25\u8bf7\u91cd\u8bd5\uff01"),e.abrupt("return",!1);case 18:case"end":return e.stop()}}),e,null,[[1,13]])})));return function(t){return e.apply(this,arguments)}}(),H=function(){var e=Object(b["a"])(m.a.mark((function e(t){var a,n;return m.a.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return a=p["a"].loading("\u6b63\u5728\u66f4\u65b0"),e.prev=1,e.next=4,B(t);case 4:if(n=e.sent,a(),!E["a"].isOk(n)){e.next=9;break}return p["a"].success("\u66f4\u65b0\u6210\u529f"),e.abrupt("return",!0);case 9:return p["a"].warn(n.msg),e.abrupt("return",!1);case 13:return e.prev=13,e.t0=e["catch"](1),a(),p["a"].error("\u66f4\u65b0\u5931\u8d25\u8bf7\u91cd\u8bd5\uff01"),e.abrupt("return",!1);case 18:case"end":return e.stop()}}),e,null,[[1,13]])})));return function(t){return e.apply(this,arguments)}}(),J=function(){var e=Object(b["a"])(m.a.mark((function e(t){var a,n;return m.a.wrap((function(e){while(1)switch(e.prev=e.next){case 0:if(a=p["a"].loading("\u6b63\u5728\u5220\u9664"),t){e.next=3;break}return e.abrupt("return",!0);case 3:return e.prev=3,e.next=6,N(t.map((function(e){return e.TABLE_NAME})));case 6:if(n=e.sent,a(),!E["a"].isOk(n)){e.next=11;break}return p["a"].success("\u5220\u9664\u6210\u529f\uff0c\u5373\u5c06\u5237\u65b0"),e.abrupt("return",!0);case 11:return p["a"].warn(n.msg),e.abrupt("return",!1);case 15:return e.prev=15,e.t0=e["catch"](3),a(),p["a"].error("\u5220\u9664\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5"),e.abrupt("return",!1);case 20:case"end":return e.stop()}}),e,null,[[3,15]])})));return function(t){return e.apply(this,arguments)}}();t["default"]=function(){var e=Object(f["useRef"])(),t=Object(f["useState"])(!1),a=Object(i["a"])(t,2),o=a[0],p=a[1],E=Object(f["useState"])(!1),C=Object(i["a"])(E,2),T=C[0],A=C[1],N=Object(f["useState"])(void 0),g=Object(i["a"])(N,2),B=g[0],I=g[1],S=[{title:"\u5e8f\u53f7",dataIndex:"id",valueType:"indexBorder",width:64},{title:"\u8868\u540d",dataIndex:"TABLE_NAME",key:"tableName",order:2,formItemProps:{allowClear:!0}},{title:"\u884c",dataIndex:"TABLE_ROWS",hideInSearch:!0},{title:"\u521b\u5efa\u65f6\u95f4",dataIndex:"CREATE_TIME",valueType:"dateRange",key:"createdAt"},{title:"\u6ce8\u91ca",dataIndex:"TABLE_COMMENT",key:"comment",order:1,formItemProps:{allowClear:!0}},{title:"\u64cd\u4f5c",dataIndex:"option",valueType:"option",fixed:"right",render:function(e,t){return h.a.createElement(h.a.Fragment,null,h.a.createElement(k["a"],{code:"sys:table:update"},h.a.createElement("a",{onClick:function(){A(!0),I({name:t.TABLE_NAME,comment:t.TABLE_COMMENT})}},"\u7f16\u8f91")),h.a.createElement(k["a"],{code:"sys:table:column:list"},h.a.createElement(s["a"],{type:"vertical"}),h.a.createElement("a",{onClick:function(){w["e"].push("/system/table/struct/".concat(t.TABLE_NAME))}},"\u7ed3\u6784")),h.a.createElement(k["a"],{code:"sys:table:data:list"},h.a.createElement(s["a"],{type:"vertical"}),h.a.createElement("a",{onClick:function(){w["e"].push("/system/table/data/".concat(t.TABLE_NAME))}},"\u6570\u636e")))}}],V=Object(j["a"])("sys:table:add"),L=Object(j["a"])("sys:table:del");return h.a.createElement(d["c"],null,h.a.createElement(v["a"],{headerTitle:"\u67e5\u8be2\u8868\u683c",actionRef:e,rowKey:"TABLE_NAME",rowSelection:{},toolBarRender:function(e,t){var a=t.selectedRows;return[V&&h.a.createElement(l["a"],{icon:h.a.createElement(O["a"],null),type:"primary",onClick:function(){I(void 0),p(!0)}},"\u65b0\u5efa"),a&&a.length>0&&h.a.createElement(r["a"],{overlay:h.a.createElement(c["a"],{onClick:function(){var t=Object(b["a"])(m.a.mark((function t(n){return m.a.wrap((function(t){while(1)switch(t.prev=t.next){case 0:"remove"===n.key&&u["a"].confirm({title:"\u5220\u9664\u63d0\u793a",content:"\u786e\u5b9a\u8981\u5220\u9664\u8fd9\u4e9b\u8868?",onOk:function(){J(a).then((function(){e.reload()}))}});case 1:case"end":return t.stop()}}),t)})));return function(e){return t.apply(this,arguments)}}(),selectedKeys:[]},L&&h.a.createElement(c["a"].Item,{key:"remove"},"\u6279\u91cf\u5220\u9664"))},h.a.createElement(l["a"],null,"\u6279\u91cf\u64cd\u4f5c ",h.a.createElement(y["a"],null)))]},request:function(){var e=Object(b["a"])(m.a.mark((function e(t){var a,r;return m.a.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return a=Object(n["a"])({},t),a.createdAt&&(a.createdAt=t.createdAt.map((function(e){return e.split(" ")[0]})),a.createdAt[0]+=" 00:00:00",a.createdAt[1]+=" 23:59:59"),e.next=4,x(a);case 4:return r=e.sent,e.abrupt("return",r.data);case 6:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}(),columns:S}),o&&h.a.createElement(q,{initVals:B,modalVisible:o,onSubmit:function(){var t=Object(b["a"])(m.a.mark((function t(a){var n;return m.a.wrap((function(t){while(1)switch(t.prev=t.next){case 0:return t.next=2,K(a);case 2:return n=t.sent,n&&(I(void 0),setTimeout((function(){p(!1),e.current&&e.current.reload()}),0)),t.abrupt("return",n);case 5:case"end":return t.stop()}}),t)})));return function(e){return t.apply(this,arguments)}}(),onCancel:function(){I(void 0),setTimeout((function(){p(!1)}),0)}}),T&&h.a.createElement(P,{initVals:B,modalVisible:T,onSubmit:function(){var t=Object(b["a"])(m.a.mark((function t(a){var n;return m.a.wrap((function(t){while(1)switch(t.prev=t.next){case 0:return t.next=2,H(a);case 2:return n=t.sent,n&&(A(!1),e.current&&e.current.reload()),t.abrupt("return",n);case 5:case"end":return t.stop()}}),t)})));return function(e){return t.apply(this,arguments)}}(),onCancel:function(){I(void 0),setTimeout((function(){A(!1)}),0)}}))}}}]);