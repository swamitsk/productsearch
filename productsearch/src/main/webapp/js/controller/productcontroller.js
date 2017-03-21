(function (){

	angular
		.module("productFinder")
		.controller("productcontroller",ProductController);
	
		
		function ProductController($http){

			var vm = this;
			vm.currentSelection = null;
			vm.RetrieveObjectWeight = RetrieveObjectWeight;
			//vm.response;
			vm.categories;
			vm.average;
			vm.objectContent;
			console.log("swami");
			$http.get('webapi/productsearch').then(saveResponse);
			
			
			
			function saveResponse(response)
			{
				vm.categories = response.data.name;
			//	vm.currentSelection = vm.categories[0];
				//console.log(vm.currentSelection);
			}
			
			function RetrieveObjectWeight()
			{
				console.log(vm.currentSelection);
				$http.get('webapi/productsearch/weight/'+ vm.currentSelection).then(parseWeightResponse);
			}
			
			function parseWeightResponse(response)
			{
				vm.objectContent = response.data.allObj[0];
				console.log(vm.objectContent);
				vm.average = response.data.averageWeight;
			}
		}

})();	
