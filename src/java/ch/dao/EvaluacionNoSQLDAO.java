package ch.dao;

import ch.dto.EvaluacionDTO;

public class EvaluacionNoSQLDAO {

    public boolean guardarEnMongoDB(EvaluacionDTO evaluacion) {
        // En una implementación real con el Driver NoSQL, aquí se inicializaría el MongoClient:
        // MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        // MongoDatabase database = mongoClient.getDatabase("DB_Documental");
        // MongoCollection<Document> collection = database.getCollection("Coleccion_Evaluacion");

        System.out.println("--- PERSISTENCIA EN MICROSERVICIO NOSQL (MONGODB) ---");
        System.out.println("Colección Destino: Coleccion_Evaluacion");
        System.out.println("Documento Insertado -> [Estrellas: " + evaluacion.getEstrellas()
                + ", Versión de App: " + evaluacion.getVersionApp() + "]");
        System.out.println("Comentario Anonimizado Guardado Correctamente.");

        return true;
    }
}