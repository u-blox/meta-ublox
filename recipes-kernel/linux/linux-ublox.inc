DESCRIPTION = "u-blox Linux kernel"
SECTION = "kernel"
LICENSE = "GPLv2"

inherit kernel
require recipes-kernel/linux/linux-dtb.inc

PV = "${LINUX_VERSION}+git${SRCPV}"

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

S = "${WORKDIR}/git"

SRC_URI = " \
    git://${UBLOX_GIT}/linux.git;protocol=${UBLOX_GIT_PROTOCOL};nobranch=1 \
    file://defconfig \
    "

COMPATIBLE_MACHINE_elin-w160-evk = "elin-w160-evk"
