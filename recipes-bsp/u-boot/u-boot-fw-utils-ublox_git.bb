DESCRIPTION = "U-boot bootloader fw_printenv/setenv utils"
LICENSE = "GPLv2+"
SECTION = "bootloader"
DEPENDS = "mtd-utils"

LIC_FILES_CHKSUM = " \
    file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    "

SRCREV = "8db1ee302594f085d1c8f74ecfca9b15475934ce"

PV = "v2013.10+git${SRCPV}"

SRC_URI = " \
    git://${UBLOX_GIT}/u-boot.git;protocol=${UBLOX_GIT_PROTOCOL};nobranch=1 \
    file://fw_env.config \
    "

S = "${WORKDIR}/git"

EXTRA_OEMAKE = 'HOSTCC="${CC}" HOSTSTRIP="true"'

inherit uboot-config

do_compile () {
    oe_runmake ${UBOOT_MACHINE}
    oe_runmake env
}

do_install () {
    install -d ${D}${base_sbindir}
    install -m 755 ${S}/tools/env/fw_printenv ${D}${base_sbindir}/fw_printenv
    install -m 755 ${S}/tools/env/fw_printenv ${D}${base_sbindir}/fw_setenv

    install -d ${D}/${sysconfdir}
    install -m 644 ${WORKDIR}/fw_env.config ${D}/${sysconfdir}/fw_env.config
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE_elin-w160-evk = "elin-w160-evk"
