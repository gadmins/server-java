(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[20],{x6Ju:function(e,t,a){"use strict";a.r(t);var n=a("o0o1"),r=a.n(n),c=a("VTBJ"),i=a("HaE+"),u=(a("+L6B"),a("2/Rp")),o=a("ODXe"),d=a("Hx5s"),s=a("q1tI"),p=a.n(s),l=a("Qiat"),f=a("Ty5D"),b=a("9kvl"),h=a("5bA4"),w=a("sy1d");function O(e){return m.apply(this,arguments)}function m(){return m=Object(i["a"])(r.a.mark((function e(t){return r.a.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.abrupt("return",Object(w["c"])("/adminapi/db/data",{params:t}));case 1:case"end":return e.stop()}}),e)}))),m.apply(this,arguments)}var v=a("H6WC");t["default"]=function(){var e=Object(f["l"])(),t=e.name,a=Object(s["useRef"])(),n=Object(s["useState"])([]),m=Object(o["a"])(n,2),x=m[0],j=m[1];return Object(s["useEffect"])((function(){Object(v["b"])({currentPage:0,pageSize:100,tableName:t}).then((function(e){if(w["a"].isOk(e)){var t=e.data.data,a=void 0===t?[]:t,n=[];a.forEach((function(e){var t={title:e.COLUMN_NAME,dataIndex:e.COLUMN_NAME,hideInSearch:!0};"id"===t.dataIndex?(t.width=64,t.fixed="left"):"enable"===t.dataIndex&&(t.render=function(e){return e?"\u662f":"\u5426"}),n.push(t)})),n.push({key:"operation",fixed:"right",width:100}),j(n)}}))}),[]),p.a.createElement(d["c"],{title:"\u8868\u6570\u636e",content:p.a.createElement(u["a"],{icon:p.a.createElement(h["a"],null),onClick:function(){b["e"].goBack()}},"\u8fd4\u56de")},p.a.createElement(l["a"],{headerTitle:"\u8868\u6570\u636e - ".concat(t),actionRef:a,rowKey:"id",scroll:{x:1550},rowSelection:{},request:function(){var e=Object(i["a"])(r.a.mark((function e(a){var n,i;return r.a.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return n=Object(c["a"])({},a),n.tableName=t,n.createdAt&&(n.createdAt=a.createdAt.map((function(e){return e.split(" ")[0]})),n.createdAt[0]+=" 00:00:00",n.createdAt[1]+=" 23:59:59"),e.next=5,O(n);case 5:return i=e.sent,e.abrupt("return",i.data);case 7:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}(),columns:x}))}}}]);