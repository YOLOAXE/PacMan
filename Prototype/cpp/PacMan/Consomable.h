#ifndef __CONSOMABLE__
#define __CONSOMABLE__

#include "GameEngine.hpp"

class Consomable
{
public:
	Consomable(unsigned int consomeScore = 0);
	unsigned int getConsomeScore();
	virtual void consome();
protected:
	unsigned int m_consomeScore;
};

#endif //__CONSOMABLE__

