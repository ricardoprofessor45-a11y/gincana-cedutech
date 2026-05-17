package br.com.cedutech.gincana.storage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public class JsonStorage<T> {

	private final ObjectMapper mapper;
    private final File file;
    private final Class<T> clazz;

    public JsonStorage(String fileName, Class<T> clazz) {
        this.mapper = new ObjectMapper();
        this.mapper.findAndRegisterModules(); // <<< LINHA CRÍTICA

        this.file = new File("data/" + fileName);
        this.clazz = clazz;

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }


    public List<T> readAll() {
        try {
            if (!file.exists()) {
                return new ArrayList<>();
            }

            CollectionType type = mapper.getTypeFactory()
                    .constructCollectionType(List.class, clazz);

            return mapper.readValue(file, type);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler arquivo JSON", e);
        }
    }

    public void saveAll(List<T> data) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, data);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar arquivo JSON", e);
        }
    }
}
