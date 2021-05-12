<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Tunnistautuminen</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
		<link rel="stylesheet" href="./styles/style.css">
	</head>
	<body>
	<div class="container">
		<div class="row">
		<h1>Tunnistautuminen</h1>
		<p>Ole hyv‰ ja tunnistaudu palveluun k‰ytt‰‰ksesi kaikkia sen ominaisuuksia.</p>
		<div class="col-md-6">
			<!--  lisays  -->
			<form method="post">
				<div class="form-group">
					<label class="input-group-append" for="salasana">Salasana</label>
					<input class="form-control" name="salasana" type="password" placeholder="**" required/>
				</div>
				<div class="form-group" style="margin-top: 10px;">
					<button type="submit" class="btn btn-primary btn-lg">Tunnistaudu</button>
					<button type="button" class="btn btn-secondary btn-lg" onclick="backToIndex()">Palaa edelliselle sivulle</button>
				</div>
				</div>
			</form>
			 <p>&copy; Lehtokari.S 2021 - Ohjelmointi 2</p>
			</div>
		</div>
		<script src="./js/app.js"></script>
	</body>
</html>