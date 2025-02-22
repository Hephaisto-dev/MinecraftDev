/*
 * Minecraft Dev for IntelliJ
 *
 * https://minecraftdev.org
 *
 * Copyright (c) 2022 minecraft-dev
 *
 * MIT License
 */

package com.demonwav.mcdev.platform.mcp.aw.psi.mixins

interface AwMethodEntryMixin : AwEntryMixin {

    val methodName: String?
    val methodDescriptor: String?
}
