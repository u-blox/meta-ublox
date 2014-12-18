require recipes-bsp/u-boot/u-boot.inc

# To build u-boot for your machine, provide the following lines in your machine
# config, replacing the assignments as appropriate for your machine.
# UBOOT_MACHINE = "omap3_beagle_config"
# UBOOT_ENTRYPOINT = "0x80008000"
# UBOOT_LOADADDRESS = "0x80008000"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = " \
    file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    "

SRCREV = "8db1ee302594f085d1c8f74ecfca9b15475934ce"

PV = "v2013.10+git${SRCPV}"

SRC_URI = "git://${UBLOX_GIT}/u-boot.git;protocol=${UBLOX_GIT_PROTOCOL};nobranch=1"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

UBOOT_SUFFIX = "img"
SPL_BINARY = "MLO"

SPL_BIN ?= "u-boot-spl.bin"
SPL_BIN_IMAGE ?= "u-boot-spl-${MACHINE}-${PV}-${PR}.bin"
SPL_BIN_SYMLINK ?= "u-boot-spl-${MACHINE}.bin"

do_deploy_append() {
    if [ "x${SPL_BIN}" != "x" ]; then
        install -d ${DEPLOYDIR}
        install ${S}/spl/${SPL_BIN} ${DEPLOYDIR}/${SPL_BIN_IMAGE}

        rm -f ${DEPLOYDIR}/${SPL_BIN} ${DEPLOYDIR}/${SPL_BIN_SYMLINK}
        ln -sf ${SPL_BIN_IMAGE} ${DEPLOYDIR}/${SPL_BIN}
        ln -sf ${SPL_BIN_IMAGE} ${DEPLOYDIR}/${SPL_BIN_SYMLINK}
    fi
}
