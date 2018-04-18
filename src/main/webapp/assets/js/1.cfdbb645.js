(window.webpackJsonp=window.webpackJsonp||[]).push([[1],{44:function(t,e,r){"use strict";r.r(e);var i=r(0),o=Object(i.a)({},function(){this.$createElement;this._self._c;return this._m(0)},[function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("div",{staticClass:"content"},[r("h1",{attrs:{id:"design-document"}},[r("a",{staticClass:"header-anchor",attrs:{href:"#design-document","aria-hidden":"true"}},[t._v("#")]),t._v(" Design Document")]),r("h2",{attrs:{id:"system-requirements"}},[r("a",{staticClass:"header-anchor",attrs:{href:"#system-requirements","aria-hidden":"true"}},[t._v("#")]),t._v(" System Requirements")]),r("ul",[r("li",[t._v("Tomcat8")]),r("li",[t._v("Maven")])]),r("h2",{attrs:{id:"installation"}},[r("a",{staticClass:"header-anchor",attrs:{href:"#installation","aria-hidden":"true"}},[t._v("#")]),t._v(" Installation")]),r("pre",{pre:!0,attrs:{class:"language-text"}},[r("code",[t._v(" mvn clean package\n")])]),r("p",[t._v("Copy the outputted "),r("code",[t._v("./target/EstateAgent.war")]),t._v(" file into the "),r("code",[t._v("webapps")]),t._v(" directory\nin your tomcat installation.")]),r("p",[t._v("Restart tomcat")]),r("h2",{attrs:{id:"structure"}},[r("a",{staticClass:"header-anchor",attrs:{href:"#structure","aria-hidden":"true"}},[t._v("#")]),t._v(" Structure")]),r("h3",{attrs:{id:"objects"}},[r("a",{staticClass:"header-anchor",attrs:{href:"#objects","aria-hidden":"true"}},[t._v("#")]),t._v(" Objects")]),r("p",[t._v("There are three objects the api excepts those are "),r("code",[t._v("Property")]),t._v(", "),r("code",[t._v("Bid")]),t._v(" and\n"),r("code",[t._v("LocalDataTime")]),t._v(".")]),r("p",[t._v("A "),r("code",[t._v("LocalDataTime")]),t._v(" is from the java library and is in the form\n"),r("code",[t._v("2007-12-03T10:15:30")]),t._v(".")]),r("p",[t._v("A "),r("code",[t._v("Bid")]),t._v(" Class. The bid class is an object used to bid on a property and store\nwho has the highest bid and what amount that is. A bid can be seen in the form")]),r("table",[r("thead",[r("tr",[r("th",[t._v("Name")]),r("th",[t._v("Type")]),r("th",[t._v("Description")])])]),r("tbody",[r("tr",[r("td",[t._v("offer")]),r("td",[t._v("Int")]),r("td",[t._v("A integer of euro amount of the bid")])]),r("tr",[r("td",[t._v("bidder")]),r("td",[t._v("String")]),r("td",[t._v("A String used to identify who the bidder is")])])])]),r("p",[t._v("A "),r("code",[t._v("Property")]),t._v(" Class. This class is an object used to store a Property in the\ndatabase. A property can be seen in the form")]),r("table",[r("thead",[r("tr",[r("th",[t._v("Name")]),r("th",[t._v("Type")]),r("th",[t._v("Description")])])]),r("tbody",[r("tr",[r("td",[t._v("id")]),r("td",[t._v("int")]),r("td",[t._v("Unique id of property")])]),r("tr",[r("td",[t._v("type")]),r("td",[t._v("string")]),r("td",[t._v("What type of property is it. "),r("code",[t._v("apartment")]),t._v(" or "),r("code",[t._v("house")])])]),r("tr",[r("td",[t._v("bedrooms")]),r("td",[t._v("int")]),r("td",[t._v("Number of bedrooms in the property")])]),r("tr",[r("td",[t._v("district")]),r("td",[t._v("int")]),r("td",[t._v("The postcode representing where in Dublin they are located e.g. 12 represents D12")])]),r("tr",[r("td",[t._v("start")]),r("td",[t._v("LocalDataTime")]),r("td",[t._v("The date and time the property is created")])]),r("tr",[r("td",[t._v("end")]),r("td",[t._v("LocalDataTime")]),r("td",[t._v("The date that the auction closes on")])]),r("tr",[r("td",[t._v("price")]),r("td",[t._v("int")]),r("td",[t._v("The asking price for the property in euro")])])])]),r("h2",{attrs:{id:"route"}},[r("a",{staticClass:"header-anchor",attrs:{href:"#route","aria-hidden":"true"}},[t._v("#")]),t._v(" Route")]),r("p",[t._v("API routes for rest calls are as follows")]),r("table",[r("thead",[r("tr",[r("th",[t._v("URI path")]),r("th",[t._v("HTTP method")]),r("th",[t._v("Description")])])]),r("tbody",[r("tr",[r("td",[t._v("/api/property")]),r("td",[t._v("GET")]),r("td",[t._v("returns an array of "),r("code",[t._v("Property")]),t._v(" in "),r("code",[t._v("application/json")])])]),r("tr",[r("td",[t._v("/api/property")]),r("td",[t._v("POST")]),r("td",[t._v("create new "),r("code",[t._v("Property")]),t._v(" with "),r("code",[t._v("Property")]),t._v(" object sent returns the id of the new "),r("code",[t._v("Property")])])]),r("tr",[r("td",[t._v("/api/property/{id}")]),r("td",[t._v("GET")]),r("td",[t._v("returns "),r("code",[t._v("Property")]),t._v(" with given id in "),r("code",[t._v("application/json")])])]),r("tr",[r("td",[t._v("/api/property/{id}")]),r("td",[t._v("PUT")]),r("td",[t._v("updates "),r("code",[t._v("Property")]),t._v(" with given id to match "),r("code",[t._v("Property")]),t._v(" object sent")])]),r("tr",[r("td",[t._v("/api/property/{id}")]),r("td",[t._v("DELETE")]),r("td",[t._v("delete "),r("code",[t._v("Property")]),t._v(" with given id")])]),r("tr",[r("td",[t._v("/api/property/{id}/bid")]),r("td",[t._v("GET")]),r("td",[t._v("returns "),r("code",[t._v("Bid")]),t._v(" for "),r("code",[t._v("Property")]),t._v(" with given id in "),r("code",[t._v("application/json")])])]),r("tr",[r("td",[t._v("/api/property/{id}/bid")]),r("td",[t._v("POST")]),r("td",[t._v("excepts "),r("code",[t._v("Bid")]),t._v(" for "),r("code",[t._v("Property")]),t._v(" with given id returns http 409 with error message if bid is too low to be excepted & 201 if the id is excepted")])]),r("tr",[r("td",[t._v("/api/property/{id}/booking")]),r("td",[t._v("GET")]),r("td",[t._v("returns an Array of unbooked times in the form of "),r("code",[t._v("2007-12-03T10:15:30")]),t._v(" for "),r("code",[t._v("Property")]),t._v(" with given id in "),r("code",[t._v("application/json")])])]),r("tr",[r("td",[t._v("/api/property/{id}/booking")]),r("td",[t._v("POST")]),r("td",[t._v("excepts times in the form of "),r("code",[t._v("2007-12-03T10:15:30")]),t._v(" for "),r("code",[t._v("Property")]),t._v(" with given id returns 201 if booking is excepted an 409 with error message as reason if booking fails")])])])])])}],!1,null,null,null);e.default=o.exports}}]);