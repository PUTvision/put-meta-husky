#
# This file is the meta-husky recipe.
#
inherit ros_distro_foxy

SUMMARY = "Husky_bringup robot library"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD;md5=3775480a712fc46a69647678acb234cb"

ROS_CN = "husky"
ROS_BPN = "husky-bringup"

ROS_BUILD_DEPENDS = " \
    husky-base \
    husky-control \
    husky-description \
    robot-upstart \
"

ROS_BUILDTOOL_DEPENDS = " \
    ament-cmake-native \
"

ROS_EXPORT_DEPENDS = ""

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    std-srvs \
    tf2 \
    robot-upstart \
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


FILES:${PN}:prepend = " \
    ${datadir}/husky_bringup \
"
FILES_SOLIBSDEV = ""
FILES:${PN} += " \
    ${libdir}/lib*${SOLIBSDEV} \
    /usr/lib/husky_bringup/install \
"

ROS_BRANCH ?= "branch=put-foxy"
SRC_URI = "git://github.com/PPI-PUT/put-husky;${ROS_BRANCH};protocol=https"
SRCREV = "4305af80fe25b2821d6f671a8f4f573e32b61d01"

SRC_URI += "file://80-husky.rules"

S = "${WORKDIR}/git/husky_bringup"

do_install:append() {
    install -Dm 0644 ${WORKDIR}/git/husky_bringup/udev/10-microstrain.rules ${D}${sysconfdir}/udev/rules.d/10-microstrain.rules
    install -Dm 0644 ${WORKDIR}/git/husky_bringup/udev/41-clearpath.rules ${D}${sysconfdir}/udev/rules.d/41-clearpath.rules
    install -Dm 0644 ${WORKDIR}/git/husky_bringup/udev/41-hokuyo.rules ${D}${sysconfdir}/udev/rules.d/41-hokuyo.rules
    install -Dm 0644 ${WORKDIR}/git/husky_bringup/udev/41-logitech.rules ${D}${sysconfdir}/udev/rules.d/41-logitech.rules
    install -Dm 0644 ${WORKDIR}/git/husky_bringup/udev/52-ftdi.rules ${D}${sysconfdir}/udev/rules.d/52-ftdi.rules
    install -Dm 0644 ${WORKDIR}/80-husky.rules ${D}${sysconfdir}/udev/rules.d/80-husky.rules
}


ROS_BUILD_TYPE = "ament_cmake"


inherit ros_${ROS_BUILD_TYPE}
