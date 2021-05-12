<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page session="true" %>
<html>
	<head>
		<title>Laskuri</title>
		<!--<script src="/js/app.js"></script>-->
		<!-- CSS only -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
		<link rel="stylesheet" href="./styles/style.css">
	</head>
	<body>
	<div class="container">
		<div class="row">
		<c:if test="${not empty sessionScope.message}">
		<div class="col-md-12">
			<div class="alert alert-${sessionScope.messageStatus}" role="alert">
				<h3>Palvelimen viesti</h3>
			  	<p>${sessionScope.message}</p>
			</div>
		</div>
		</c:if>
		<h1>Puun tiheyslaskuri</h1>
		<div class="col-md-6">
		<h2>Lomake</h2>
			<!--  lisays  -->
			<form method="post">
				<div class="form-group">
					<label class="input-group-append" for="paksuus">Paksuus (mm)</label>
					<input class="form-control" id="paksuus" name="paksuus" pattern="^[0-9][\.\d]*(,\d+)?$" type="text" required/>
				</div>
				<div class="form-group">
					<label class="input-group-append" for="pituus">Pituus (mm)</label>
					<input class="form-control" id="pituus" name="pituus" pattern="^[0-9][\.\d]*(,\d+)?$"  type="text" required/>
				</div>
				<div class="form-group">
					<label class="input-group-append" for="leveys">Leveys (mm)</label>
					<input class="form-control" id="leveys" name="leveys" pattern="^[0-9][\.\d]*(,\d+)?$" type="text" required/>
				</div>
				<div class="form-group">
					<label class="input-group-append" for="paino">Paino (g)</label>
					<input class="form-control" id="paino" name="paino" pattern="^[0-9][\.\d]*(,\d+)?$" type="text" required/>
				</div>
				<c:if test="${ authenticated }">
				<div class="form-group">
					<label class="input-group-append" for="grain">Grain</label>
					<input class="form-control" id="grain" name="grain" value="" type="text"/>
				</div>
				</c:if>
				<div class="form-group" style="margin-top: 10px;">
					<button type="submit" class="btn btn-primary btn-lg">Laske tiheys</button>
					<button type="button" class="btn btn-secondary btn-lg" onclick="goToAuth()">Tunnistaudu palveluun</button>
				</div>
				</div>
				<div class="col-md-4">
					<h2>Tulokset</h2>
					<div class="form-group" style="margin-top: 10px;">
						<label class="input-group-append" for="tulos_kg">Tulos (kg/m3)</label>
						<input class="form-control" type="text" id="tulos_kg" value="${density_km}" disabled/>
					</div>
					<div class="form-group">
						<label class="input-group-append" for="tulos_lb">Tulos (lb/ft3)</label>
						<input class="form-control" type="text" id="tulos_lb" value="${density_ft}" disabled/>
					</div>
				</div>
			</form>
			<!-- taulukko -->
			<c:if test="${not empty balsaItems and authenticated}">
			<h2>Tietokannan sisältö</h2>
			<table class="table table-striped table-light">
			  <thead>
			    <tr>
			      <th scope="col">ID.</th>
			      <th scope="col">Tiheys (mm)</th>
			      <th scope="col">Grain</th>
			      <th scope="col">Paksuus (mm)</th>
			      <th scope="col">Leveys (mm)</th>
			      <th scope="col">Pituus (mm)</th>
			      <th scope="col">Paino (g)</th>
			      <th scope="col">Toiminnot</th>
			    </tr>
			  </thead>
			  <tbody>
			 <c:forEach items="${balsaItems}" var = "i">		 	
			     <tr id="${i.getId()}">
				     <td><c:out value = "${i.getId()}"/></td>
				    <% DecimalFormat decimal = new DecimalFormat("0.00"); %>
				     <td><fmt:formatNumber type = "double" pattern="#.##" value="${i.getTiheys()}" /></td>
				     <td><c:out value = "${i.getGrain()}"/></td>
				     <td><c:out value = "${i.getKorkeus()}"/></td>
				     <td><c:out value = "${i.getLeveys()}"/></td>
				     <td><c:out value = "${i.getPituus()}"/></td>
				     <td><c:out value = "${i.getPaino()}"/></td>
				     <td><button onclick="removeItem(${ i.getId() })" class="btn btn-danger">Poista</button></td>
			    </tr>
			 </c:forEach>
			  </tbody>
			</table>
			</c:if>
				<p>&copy; Lehtokari.S 2021 - Ohjelmointi 2</p>
			</div>
		</div>
		<script src="./js/app.js"></script>
	</body>
</html>