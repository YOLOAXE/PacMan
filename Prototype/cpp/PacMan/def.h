#ifndef __DEFINITION__
#define __DEFINITION__
#include <stdint.h>

//#define DEBUG_PACMAN
const uint8_t NULL_ID = 0;
const uint8_t WALL_ID = 1;
const uint8_t WALL_SPEC_ID = 2;
const uint8_t TP_ID = 3;
const uint8_t PAC_GOME_ID = 4;
const uint8_t BIG_PAC_GOME_ID = 5;
const uint8_t PLAYER_ID = 6;
const uint8_t BLINKY_ROUGE_ID = 7; //attaque directement Pac Man. Il suit Pac-Man comme son ombre.
const uint8_t PINKY_ROSE_ID = 8; //a tendance à se mettre en embuscade. Elle vise l'endroit où va se trouver Pac-Man.
const uint8_t INKY_BLEU_ID = 9; //est capricieux. De temps en temps, il part dans la direction opposée à Pac-Man.
const uint8_t CLYDE_ORANGE_ID = 10; //feint l'indifférence. De temps en temps, il choisit une direction au hasard (qui peut être celle de Pac-Man).
const uint8_t CERISE_ID = 11; // 100 pt niv 1
const uint8_t FRAISE_ID = 12; // 300 pt niv 2
const uint8_t ORANGE_ID = 13; // 500 pt niv 3-4
const uint8_t POMME_ID = 14; // 700 pt niv 5-6
const uint8_t MELON_ID = 15; // 1000 pt niv 7-8
const uint8_t GALBOSS_ID = 16; // 2000 pt niv 9-10
const uint8_t CLOCHE_ID = 17; // 3000 pt niv 11-12
const uint8_t CLE_ID = 18; // 5000 pt niv 13-+

const uint32_t PAC_GOME_PT = 10;
const uint32_t BIG_PAC_GOME_PT = 50;

const uint32_t CERISE_PT = 100;
const uint32_t FRAISE_PT = 300;
const uint32_t ORANGE_PT = 500;
const uint32_t POMME_PT = 700;
const uint32_t MELON_PT = 1000;
const uint32_t GALBOSS_PT = 2000;
const uint32_t CLOCHE_PT = 3000;
const uint32_t CLE_PT = 5000;

const int CARTE_HAUTEUR = 27;
const int CARTE_LARGEUR = 21;
const float ESPACEMENT = 4.435f;
const float OFFSET_H = -44.465f;
const float OFFSET_L = -58.885f;

const float BASE_SPEED = 0.2f;
const float MALUS_SPEED = 0.4f;
const float FUITE_SPEED = 0.1f;

const uint8_t PLAYER_BASE_LIFE = 3;

enum Direction { North, East, South, West };

const float FANTOME_TEMPS_FUITE = 7.0f;

static const uint8_t ConsomableID[10] =
{ 
	PAC_GOME_ID ,
	BIG_PAC_GOME_ID,
	CERISE_ID,
	FRAISE_ID,
	ORANGE_ID,
	POMME_ID,
	MELON_ID,
	GALBOSS_ID,
	CLOCHE_ID,
	CLE_ID
};

static const int DTOP[4][2] = { {0,-1}, {1,0}, {0,1}, {-1,0} }; //direction to position
static const float ANGLES[4] = {0.0f,270.0f,180.0f,90.0f};
//Color
static const float PACK_MAN_COLOR[3] = { 1.0f,1.0f,0.0f };
static const float PACK_GOME_COLOR[3] = { 1.0f,1.0f,0.0f };
static const float BLINKY_COLOR[3] = { 1.0f,0.0f,0.0f };
static const float PINKY_COLOR[3] = { 0.98f,0.0f,0.88f };
static const float INKY_COLOR[3] = { 0.0f,0.6f,1.0f };
static const float CLYDE_COLOR[3] = { 1.0f,0.4f,0.0f };
static const float FUITE_COLOR[3] = { 0.05f,0.05f,1.0f };
static const float FUITE_END_COLOR[3] = { 0.87f,0.87f,1.0f };
static const float DEAD_FANTOME_COLOR[3] = { 0.0f,0.0f,0.0f };
//TP
static const int TP_POINT[2][2] = { {0,13} ,{20,13} };
static const uint8_t COLIDER_CARTE_BASE[CARTE_HAUTEUR][CARTE_LARGEUR] =
{
	{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
	{1,4,4,4,4,4,4,4,4,4,1,4,4,4,4,4,4,4,4,4,1},
	{1,4,1,1,1,4,1,1,1,4,1,4,1,1,1,4,1,1,1,4,1},
	{1,5,1,1,1,4,1,1,1,4,1,4,1,1,1,4,1,1,1,5,1},
	{1,4,1,1,1,4,1,1,1,4,1,4,1,1,1,4,1,1,1,4,1},
	{1,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,1},
	{1,4,1,1,1,4,1,4,1,1,1,1,1,4,1,4,1,1,1,4,1},
	{1,4,1,1,1,4,1,4,1,1,1,1,1,4,1,4,1,1,1,4,1},
	{1,4,4,4,4,4,1,4,4,4,1,4,4,4,1,4,4,4,4,4,1},
	{1,1,1,1,1,4,1,1,1,0,1,0,1,1,1,4,1,1,1,1,1},
	{1,1,1,1,1,4,1,0,0,0,0,0,0,0,1,4,1,1,1,1,1},
	{1,1,1,1,1,4,1,0,1,1,2,1,1,0,1,4,1,1,1,1,1},
	{1,1,1,1,1,4,1,0,1,0,7,0,1,0,1,4,1,1,1,1,1},
	{3,0,0,0,0,4,0,0,1,8,9,10,1,0,0,4,0,0,0,0,3},
	{1,1,1,1,1,4,1,0,1,1,1,1,1,0,1,4,1,1,1,1,1},
	{1,1,1,1,1,4,1,0,0,0,0,0,0,0,1,4,1,1,1,1,1},
	{1,1,1,1,1,4,1,0,1,1,1,1,1,0,1,4,1,1,1,1,1},
	{1,1,1,1,1,4,1,4,1,1,1,1,1,4,1,4,1,1,1,1,1},
	{1,4,4,4,4,4,4,4,4,4,1,4,4,4,4,4,4,4,4,4,1},
	{1,4,1,1,1,4,1,1,1,4,1,4,1,1,1,4,1,1,1,4,1},
	{1,5,4,4,1,4,4,4,4,4,6,4,4,4,4,4,1,4,4,5,1},
	{1,1,1,4,1,4,1,4,1,1,1,1,1,4,1,4,1,4,1,1,1},
	{1,1,1,4,1,4,1,4,1,1,1,1,1,4,1,4,1,4,1,1,1},
	{1,4,4,4,4,4,1,4,4,4,1,4,4,4,1,4,4,4,4,4,1},
	{1,4,1,1,1,1,1,1,1,4,1,4,1,1,1,1,1,1,1,4,1},
	{1,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,1},
	{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
};
#endif //__DEFINITION__