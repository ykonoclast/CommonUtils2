/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.duckdns.spacedock.commonutils.files;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Locale;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 * classe interne encadrant les échanges avec les fichiers JSON
 *
 * @author ykonoclast
 */
class JSONHandler
{

    /**
     * le nom du répertoire de base dans lequel chercher les fichier JSON
     */
    private final String m_repertoire;

    /**
     * charge un JSON object à partir du path fourni (relatif au répertoire
     * resources/JSON du package appelant)
     *
     * @param p_path
     * @return
     * @throws FileNotFoundException
     */
    JsonObject loadJsonFile(String p_path) throws FileNotFoundException
    {
	String fullPath = m_repertoire.concat(p_path);
	if (!p_path.endsWith(".json") && !p_path.endsWith(".JSON"))
	{
	    fullPath = fullPath.concat(".json");
	}
	InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(fullPath);//on utilise le classloader du thread et pas de la classe pour plus de sûreté, on charge en effet pour un autre package
	if (in == null)
	{
	    GeneralFileHandler.getInstance(getClass().getPackage().getName()).fichIntrouvable("JSON", p_path, Locale.getDefault());
	}
	JsonReader reader = Json.createReader(in);
	return (reader.readObject());
    }

    /**
     * constructeur
     *
     * @param p_package
     */
    JSONHandler(String p_package)
    {
	m_repertoire = p_package.replace(".", "/").concat("/JSON/");
    }
}
