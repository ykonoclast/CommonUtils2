/*
 * Copyright (C) 2019 ykonoclast
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.duckdns.spacedock.commonutils.files;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.json.JsonObject;
import org.duckdns.spacedock.commonutils.files.GeneralFileHandler;

/**
 * Classe principale d'accès aux utilitaires de récupération de fichiers
 *
 * @author ykonoclast
 */
public class GeneralFileHandler
{

    /**
     * instances statiques uniques par package
     */
    private final static Map<String, GeneralFileHandler> m_instances = new HashMap<>();

    /**
     * nom du package de l'instance particulière
     */
    private final String m_packageName;

    /**
     * ensemble des StringHandlers par locale
     */
    private final Map<Locale, StringHandler> m_stringsHandlers = new HashMap<>();

    /**
     * JSONHandler de cette instance de package
     */
    private JSONHandler m_JSONHandler;

    /**
     * PropertiesHandler de cette instance de package
     */
    private PropertiesHandler m_PropertiesHandler;

    /**
     * pseudo constructeur : renvoie l'instance correspondant au package indiqué
     * et la construit si elle n'existe pas
     *
     * @param p_package
     * @return
     */
    public static GeneralFileHandler getInstance(String p_package)
    {
	if (!m_instances.containsKey(p_package))
	{
	    m_instances.put(p_package, new GeneralFileHandler(p_package));
	}

	return m_instances.get(p_package);
    }

    /**
     * véritable contructeur, appelé par getInstance() si l'instance n'existe
     * pas. Ne construit rien tout de suite : on le fera à la volée sur demande
     * uniquement
     *
     */
    private GeneralFileHandler(String p_package)
    {
	m_packageName = p_package.concat(".resources");
    }

    /**
     * Renvoie la property demandée du fichier nommé "generalstrings.properties"
     * situé dans le répertoire resources/strings du package
     *
     * @param p_property
     * @param p_locale
     * @return
     */
    public String getString(String p_property, Locale p_locale)
    {
	secureStringHandler(p_locale);

	return m_stringsHandlers.get(p_locale).getString(p_property);
    }

    /**
     * envoyer une exception IllegalArgumentException avec un message d'erreur
     * standardisé issu du fichier nommé "exceptions.properties" situé dans le
     * répertoire resources/strings du package
     *
     * @param p_propExcep
     * @param p_locale
     */
    public void paramAberrant(String p_propExcep, Locale p_locale)
    {
	paramAberrant(p_propExcep, "", p_locale);
    }

    /**
     * envoyer une exception IllegalArgumentException avec un message d'erreur
     * standardisé issu du fichier nommé "exceptions.properties" situé dans le
     * répertoire resources/strings du package ainsi qu'un complément
     * personnalisé
     *
     * @param p_propExcep
     * @param p_complementTexte
     * @param p_locale
     */
    public void paramAberrant(String p_propExcep, String p_complementTexte, Locale p_locale)
    {
	String msg = baseExcep("param_aberr", p_propExcep, p_locale).concat(p_complementTexte);
	throw new IllegalArgumentException(msg);
    }

    /**
     * envoyer une exception IllegalStateException avec un message d'erreur
     * standardisé issu du fichier nommé "exceptions.properties" situé dans le
     * répertoire resources/strings du package
     *
     * @param p_propExcep
     * @param p_locale
     */
    public void mauvaiseMethode(String p_propExcep, Locale p_locale)
    {
	mauvaiseMethode(p_propExcep, "", p_locale);
    }

    /**
     * envoyer une exception IllegalStateException avec un message d'erreur
     * standardisé issu du fichier nommé "exceptions.properties" situé dans le
     * répertoire resources/strings du package ainsi qu'un complément
     * personnalisé
     *
     * @param p_propExcep
     * @param p_complementTexte
     * @param p_locale
     */
    public void mauvaiseMethode(String p_propExcep, String p_complementTexte, Locale p_locale)
    {
	String msg = baseExcep("mauv_meth", p_propExcep, p_locale).concat(p_complementTexte);
	throw new IllegalStateException(msg);
    }

    /**
     * envoyer une exception FileNotFoundException avec un message d'erreur
     * standardisé issu du fichier nommé "exceptions.properties" situé dans le
     * répertoire resources/strings du package
     *
     * @param p_propExcep
     * @param p_locale
     * @throws java.io.FileNotFoundException
     */
    public void fichIntrouvable(String p_propExcep, Locale p_locale) throws FileNotFoundException
    {
	fichIntrouvable(p_propExcep, "", p_locale);
    }

    /**
     * envoyer une exception FileNotException avec un message d'erreur
     * standardisé issu du fichier nommé "exceptions.properties" situé dans le
     * répertoire resources/strings du package ainsi qu'un complément
     * personnalisé
     *
     * @param p_propExcep
     * @param p_complementTexte
     * @param p_locale
     * @throws java.io.FileNotFoundException
     */
    public void fichIntrouvable(String p_propExcep, String p_complementTexte, Locale p_locale) throws FileNotFoundException
    {
	String msg = baseExcep("fich_introuv", p_propExcep, p_locale).concat(p_complementTexte);
	throw new FileNotFoundException(msg);
    }

    /**
     * méthode utilitaire construisant la base du message d'erreur ensuite
     * utilisé par toutes les méthodes relatives aux exceptions
     *
     * @param p_typeExcep en fonction des strings définies pour commonutils
     * @param p_propExcep
     * @param p_locale
     * @return
     */
    private String baseExcep(String p_typeExcep, String p_propExcep, Locale p_locale)
    {
	secureStringHandler(p_locale);

	String message = getInstance(getClass().getPackage().getName()).getString(p_typeExcep, p_locale);//le message d'érreur standard : issu de commonutils
	message = message.concat(m_stringsHandlers.get(p_locale).getErrorMessage(p_propExcep));//le message d'erreur fourni par le package appelant
	return (message);
    }

    /**
     * méthode fournissant un StringHandler correspondat à la locale à la
     * demande : il est créé si il n'existe pas déjà
     *
     * @param p_locale
     */
    private void secureStringHandler(Locale p_locale)
    {
	if (!m_stringsHandlers.containsKey(p_locale))
	{
	    StringHandler handler = new StringHandler(m_packageName, p_locale);
	    m_stringsHandlers.put(p_locale, handler);
	}
    }

    /**
     * renvoie un objet JSON racine de son fichier situé dans le répertoire
     * resources/JSON du package appelant
     *
     * @param p_baseFileName le nom du fichier et PAS son chemin complet
     * @return
     * @throws FileNotFoundException
     */
    public JsonObject loadJsonFile(String p_baseFileName) throws FileNotFoundException
    {
	if (m_JSONHandler == null)
	{
	    m_JSONHandler = new JSONHandler(m_packageName);
	}

	return m_JSONHandler.loadJsonFile(p_baseFileName);
    }

    /**
     * renvoie une propriété définie par son nom et située dans un fichier situé
     * dans le répertoire resources/app du package appelant
     *
     * @param p_baseFileName le nom du fichier et PAS son chemin complet
     * @param p_property
     * @return
     * @throws java.io.FileNotFoundException
     */
    public String getAppProperty(String p_baseFileName, String p_property) throws FileNotFoundException, IOException
    {
	if (m_PropertiesHandler == null)
	{
	    m_PropertiesHandler = new PropertiesHandler(m_packageName);
	}

	return m_PropertiesHandler.getAppProperty(p_baseFileName, p_property);
    }
}
