#ifndef __ENGINE_LIGHT__
#define __ENGINE_LIGHT__

#define GLM_FORCE_RADIANS
#define GLM_FORCE_DEPTH_ZERO_TO_ONE
#include "glm/glm.hpp"
#include "glm/gtc/quaternion.hpp"
#include "glm/gtx/euler_angles.hpp"
#include "glm/common.hpp"
#include "UniformBufferLight.hpp"
#include "Debug.hpp"
#include "VulkanMisc.hpp"
#include "BufferManager.hpp"
#include "GObject.hpp"

namespace Ge
{
	class Lights : public GObject
	{
	public:
		Lights(int index, VulkanMisc *vM);
		void setColors(glm::vec3 color);
		glm::vec3 getColors();
		void setSpecular(glm::vec3 spec);
		glm::vec3 getSpecular();
		void setAmbiant(glm::vec3 ambiant);
		glm::vec3 getAmbiant();
		void setRange(float r);
		float getRange();
		int getStatus(); //Statut directional spotlight pointlight
		int getIndex();
		void setIndex(int i);
		VkBuffer getUniformBuffers();
		void updateUniformBufferLight();
		void mapMemory();
		void onGUI() override;
		void setShadow(bool state);
		bool getShadow();
		virtual ~Lights();

	protected:
		UniformBufferLight m_ubl{};
		VulkanMisc *vMisc;
		VmaBuffer m_vmaUniformBuffer;
		VmaBuffer m_vmaOffScreenShadowBuffer;
		float m_nearPlane = 1.0f;
		float m_farPlane = 7.5f;
		int m_index = 0;
		bool m_shadow = false;
	};
}

#endif //__ENGINE_LIGHT__
