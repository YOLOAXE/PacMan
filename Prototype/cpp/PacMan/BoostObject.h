#pragma once
#include "PacObject.h"
#include "Player.h"

class BoostObject
{
private:
	virtual void boostPlayer(Player * p) = 0;
};

