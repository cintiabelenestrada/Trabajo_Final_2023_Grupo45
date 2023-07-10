package ar.edu.unju.fi.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploadFile {

public final static String IMAGES_FOLDER="src/main/resources/static/images_upload";
	
	/**
	 * Metodo que sube el archivo al proyecto
	 * @param filename representa el nombre del archivo que se va a subir al proyecto
	 * @return el archivo subido al proyecto
	 * @throws MalformedURLException
	 */
	public  Resource load(String filename) throws MalformedURLException {
		Path path = getPath(filename);
		Resource resource = new UrlResource(path.toUri());
		return resource;
	}
	
	/**
	 * Metodo que devuelve la ruta donde se ubica el archivo en el proyecto
	 * @param filename representa el nombre del archivo 
	 * @return la ruta donde se ubica el proyecto
	 */
	public  Path getPath(String filename) {
		return Paths.get(IMAGES_FOLDER).resolve(filename).toAbsolutePath();
	}
	
	/**
	 * Metodo que copia el archivo al proyecto
	 * @param file representa el archivo que se va a copiar
	 * @return el nombre del archivo
	 * @throws IOException
	 */
	public  String copy(MultipartFile file) throws IOException{
		String uniqueFilename =  file.getOriginalFilename();
		Path rootPath = getPath(uniqueFilename);
		Files.copy(file.getInputStream(), rootPath);
		return uniqueFilename;
	}
	
	/**
	 * Metodo que elimina el archivo del proyecto
	 * @param filename representa el nombre del archivo que se va a eliminar
	 * @return true si se elimino el archivo 
	 * @return false si no se elimino el archivo
	 */
	public boolean delete(String filename) {
		Path rootPath = getPath(filename);
		File file = rootPath.toFile();
		if(file.exists() && file.canRead()) {
			if(file.delete()) {
				return true;
			}
		}
		return false;
	}

}