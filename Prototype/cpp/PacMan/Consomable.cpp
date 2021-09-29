#include "Consomable.h"

Consomable::Consomable(unsigned int consomeScore)
{
	m_consomeScore = consomeScore;
}

unsigned int Consomable::getConsomeScore()
{
	return m_consomeScore;
}

void Consomable::consome()
{
	Debug::Log("Consome");
}