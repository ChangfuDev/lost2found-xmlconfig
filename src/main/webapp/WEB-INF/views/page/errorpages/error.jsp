<%--
  Created by IntelliJ IDEA.
  User: Sevenleaf
  Date: 2018-07-31
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<title>NullPointer Exception Error | ibard</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<style>
		/*! Spectre.css v0.5.0 | MIT License | github.com/picturepan2/spectre */
		html {
			font-family: sans-serif;
			-webkit-text-size-adjust: 100%;
			-ms-text-size-adjust: 100%
		}
		body {
			margin: 0
		}
		footer {
			display: block
		}
		a {
			background-color: transparent;
			-webkit-text-decoration-skip: objects
		}
		a:active,
		a:hover {
			outline-width: 0
		}
		::-webkit-file-upload-button {
			-webkit-appearance: button;
			font: inherit
		}
		*,
		::after,
		::before {
			box-sizing: inherit
		}
		html {
			box-sizing: border-box;
			font-size: 20px;
			line-height: 1.5;
			-webkit-tap-highlight-color: transparent
		}
		body {
			background: #fff;
			color: #50596c;
			font-family: -apple-system, system-ui, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", sans-serif;
			font-size: .8rem;
			overflow-x: hidden;
			text-rendering: optimizeLegibility
		}
		a {
			color: #5755d9;
			outline: 0;
			text-decoration: none
		}
		a:focus {
			box-shadow: 0 0 0 .1rem rgba(87, 85, 217, .2)
		}
		a:active,
		a:focus,
		a:hover {
			color: #4240d4;
			text-decoration: underline
		}
		.h1 {
			font-weight: 500
		}
		.h1 {
			font-size: 2rem
		}
		p {
			margin: 0 0 1rem
		}
		a {
			-webkit-text-decoration-skip: ink edges;
			text-decoration-skip: ink edges
		}
		.form-input:not(:placeholder-shown):invalid {
			border-color: #e85600
		}
		.form-input:not(:placeholder-shown):invalid:focus {
			box-shadow: 0 0 0 .1rem rgba(232, 86, 0, .2)
		}
		.label {
			background: #f0f1f4;
			border-radius: .1rem;
			color: #5b657a;
			display: inline-block;
			line-height: 1.2;
			padding: .1rem .15rem
		}
		.label.label-rounded {
			border-radius: 5rem;
			padding-left: .4rem;
			padding-right: .4rem
		}
		.container {
			margin-left: auto;
			margin-right: auto;
			padding-left: .4rem;
			padding-right: .4rem;
			width: 100%
		}
		.container.grid-lg {
			max-width: 976px
		}
		.container.grid-sm {
			max-width: 616px
		}
		.empty {
			background: #f8f9fa;
			border-radius: .1rem;
			color: #667189;
			padding: 3.2rem 1.6rem;
			text-align: center
		}
		.empty .empty-title {
			margin: .4rem auto
		}
		.empty .empty-action {
			margin-top: .8rem
		}
		.text-error {
			color: #e85600
		}
		.divider {
			display: block;
			position: relative
		}
		.divider {
			border-top: .05rem solid #e7e9ed;
			height: .05rem;
			margin: .4rem 0
		}
		.container::after {
			clear: both;
			content: "";
			display: table
		}
		.centered {
			display: block;
			float: none;
			margin-left: auto;
			margin-right: auto
		}
		.valign {
			display: -webkit-box!important;
			display: -webkit-flex!important;
			display: -ms-flexbox!important;
			display: flex!important;
			-webkit-box-align: center!important;
			-webkit-align-items: center!important;
			-ms-flex-align: center!important;
			align-items: center!important;
		}
		.section-footer {
			color: #acb3c2;
			padding: 2rem .5rem 0 .5rem;
			position: relative;
			z-index: 200;
		}
		.section-footer a {
			color: #667189;
		}
		.cferror_details {
			text-align: center;
		}
		.cf-error-details h1 {
			font-size: 1.2rem!important
		}
		.cf-error-details p {
			font-size: .7rem!important
		}
		.cf-error-details li {
			font-size: .65rem!important;
			list-style: none;
		}
	</style>
</head>

<body>
<div class="empty valign" style="height:100vh">
	<div class="centered">
		<p class="empty-title h1">NullPointerException Error</p>
		(::服务器内部运行出错, Exception happened::)
		<div class="divider"></div>
		<footer class="section section-footer">
			<div id="copyright" class="grid-footer container grid-lg">©
				<span year></span>
				<span class="text-error">♥</span>
				<a href="#" target="_blank">ibard</a>
			</div>
		</footer>
	</div>

</div>
<script>var copyrightNow = (new Date).getFullYear(), textContent = document.querySelector("span[year]"); copyrightSince = 2016, textContent.textContent = copyrightSince === copyrightNow ? copyrightNow : copyrightSince + " - " + copyrightNow;</script>
</body>

</html>
