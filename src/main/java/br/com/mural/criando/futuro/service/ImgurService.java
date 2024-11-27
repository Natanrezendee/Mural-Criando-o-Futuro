package br.com.mural.criando.futuro.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImgurService {

    private static final String IMGUR_CLIENT_ID = "151c60fcb07a72e"; // Substitua pelo seu Client ID

    public String uploadImage(MultipartFile file) throws Exception {
        // Crie a URL da API do Imgur
        String url = "https://api.imgur.com/3/image";

        // Crie um cabeçalho para a requisição
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Client-ID " + IMGUR_CLIENT_ID);

        // Prepare o arquivo para envio\
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", file.getBytes());

        // Crie a requisição com o cabeçalho e o arquivo
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // Use RestTemplate para enviar a requisição POST para o Imgur
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        // Analise a resposta para obter a URL da imagem
        if (response.getStatusCode() == HttpStatus.OK) {
            // Use o ObjectMapper para parsear o JSON
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = objectMapper.readTree(response.getBody());

            // Acesse o campo "link" dentro de "data"

            // Retorna o link da imagem
            return jsonResponse.path("data").path("link").asText();
        }  else {
            throw new Exception("Erro no upload da imagem.");
        }
    }
}
