package fr.upec.PacMan.MODEL;
import java.util.*;
/*
 * Nom de classe : EffectObject
 *
 * Description   : Les objects � effet qui peuve par exemple donner une amelioration � un Actor
 */

public interface EffetObject
{
	boolean hasEffect();
	void initEffect(java.util.List<Actor> users);
	boolean effet(java.util.List<Actor> users,float deltaTime);
	void endEffect(java.util.List<Actor> users);
}