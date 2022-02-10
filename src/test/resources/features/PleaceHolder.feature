#language: pt
#Author: Iasmin Silva
#Version: 1:0
#Ecoding: UTF-8

@PlaceHolder
  Funcionalidade: Criar, editar e deletar contas de usuários
    Eu como Administrador do sistema, quero acadastrar, editar e excluir usuários do sistema

  @POST
  Cenario: Cadastrar novo usuário ns API Place Holder
    Dado que a Api PlaceHolder não requer token
    Quando envio um request de cadastro de usuário com dados validos
    Entao o usuário deve ser criado corretamente
    E o status code deve ser 201

  @GET
  Cenario: Buscar um usuário existente na API PlaceHolder
    Dado que a Api PlaceHolder não requer token
    E existe um usuário cadastrado na api
    Quando buscar esse usuário
    Entao os dados do usuário devem ser retornados
    E o status code deve ser 200

  @PUT
  Cenario: Alterar usuário existente na API PlaceHolder
    Dado que a Api PlaceHolder não requer token
    E existe um usuário cadastrado na api
    Quando altero os dados do usuário
    Entao o usuário deve ser alterado com sucesso
    Entao o status code deve ser 200

  @PATCH
  Cenario:Alterar um usuário existente na API PlaceHolder PATCH
    Dado que a Api PlaceHolder não requer token
    E existe um usuário cadastrado na api
    Quando altero um ou mais dados do usuário
    Entao os dados deve ser alterado com sucesso
    E o status code deve ser 200

  @DELETE
  Cenario: Deleta um usuário existente na API PleaseHolder
    Dado que a Api PlaceHolder não requer token
    E existe um usuário cadastrado na api
    Quando deleto esse usuário
    Entao o usuário é deletado corretamente
    E o status code deve ser 200


