function exibir(apagou=false){
    if(!apagou)
        event.preventDefault();
    var filtro=document.getElementById("filtro").value;
    const URL_TO_FETCH='ServletGenero?acao=consultar&filtro='+filtro;
    fetch(URL_TO_FETCH,{method:'get'}).then(function(response){
        response.text().then(function(result)
        {
            document.getElementById('preview').innerHTML = result;
        });
    }).catch (function(err) {console.error(err);});

}


function apagaAltera(acao, cod){   
    var url = "";
    switch (acao){
        case "apagar":
            url = "ServletGenero?acao=apagar&cod=" + cod;
            break;
        case "alterar":
            url = "ServletGenero?acao=alterar&cod=" + cod;
            break;
    }
    
    fetch(url,{method:'get'}).then(function(response){
        response.text().then(function (result){
            if (acao === 'apagar'){
                document.getElementById("erromsg").innerHTML = result;
                exibir(true);
            } 
            else{
                var aux = result;
                var genero = aux.split(",");
                var form = document.forms["dados"];
                
                form.cod.value = genero[0].trim();
                form.nome.value = genero[1].trim();
            }
        });
    }).catch (function(err) {console.error(err);});

}

function gravar(){
    event.preventDefault();

    const URL_TO_FETCH = 'ServletGenero';
    
    const data = new URLSearchParams();
    for (const pair of new FormData(document.getElementById('fdados'))) {
        data.append(pair[0], pair[1]);
    }
    data.append('acao', 'confirmar');

    fetch(URL_TO_FETCH, { method: 'post', body: data 
    }).then(function (response) {
        return response.text();
    }).then(function (retorno) {
        if (retorno.startsWith('Erro')){
            document.getElementById('erromsg').innerHTML = retorno;
            document.getElementById('erro').style.display = "block";
        } 
        else{
            document.getElementById('fdados').reset();            
            exibir(true);
        }
         
    }).catch(function (error) {
        console.error(error);
    });
      
}


