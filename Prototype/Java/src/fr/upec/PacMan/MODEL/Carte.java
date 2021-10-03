package fr.upec.PacMan.MODEL;
import java.util.*;
/*
 * Nom de classe : Carte
 *
 * Description   : Carte dynamique des element;
 */

public class Carte 
{
    private int[][] m_spawnCarte;
    private Consumable[][] m_consumables;
    private int m_hauteur;
    private int m_largeur;
    private Map<Vector2,Vector2> m_mapTeleportation = new Map<Vector2,Vector2>();

    Carte(String path)
    {
        
    }

    public int[][] getSpawnCarte()
    {
        return m_spawnCarte;
    }

    public int getH()
    {
        return m_hauteur;
    }

    public int getL()
    {
        return m_largeur;
    }
}
