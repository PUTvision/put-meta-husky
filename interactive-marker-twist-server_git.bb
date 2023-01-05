#
# This file is the meta-husky recipe.
#
inherit ros_distro_foxy

SUMMARY = "interactive_marker_twist_server library"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD;md5=3775480a712fc46a69647678acb234cb"

ROS_CN = "husky"
ROS_BPN = "interactive-marker-twist-server"

ROS_BUILD_DEPENDS = " \
    geometry-msgs \
    interactive-markers \
    rclcpp \
    tf2 \
    visualization-msgs \
"

ROS_BUILDTOOL_DEPENDS = " \
    ament-cmake-native \
"

ROS_EXPORT_DEPENDS = ""

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    bash \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""
#
DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# # Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# # don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"
#
RDEPENDS:${PN} += "${ROS_EXEC_DEPENDS}"

FILES:${PN} += " \
    /usr/lib/interactive_marker_twist_server \
    /usr/share/interactive_marker_twist_server \
"


ROS_BRANCH ?= "branch=foxy-devel"
SRC_URI = "git://github.com/ros-visualization/interactive_marker_twist_server;${ROS_BRANCH};protocol=https"
SRCREV = "e7ca2f4f7e159011e8d40ab58d48aa57e8e24415"

S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "ament_cmake"
inherit ros_${ROS_BUILD_TYPE}
