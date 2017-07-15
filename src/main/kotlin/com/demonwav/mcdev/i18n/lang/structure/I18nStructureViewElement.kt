/*
 * Minecraft Dev for IntelliJ
 *
 * https://minecraftdev.org
 *
 * Copyright (c) 2017 minecraft-dev
 *
 * MIT License
 */

package com.demonwav.mcdev.i18n.lang.structure

import com.demonwav.mcdev.i18n.lang.I18nFile
import com.demonwav.mcdev.i18n.lang.gen.psi.I18nProperty
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.navigation.ItemPresentation
import com.intellij.navigation.NavigationItem
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import com.intellij.psi.util.PsiTreeUtil
import java.util.ArrayList

class I18nStructureViewElement(private val element: PsiElement) : StructureViewTreeElement, SortableTreeElement {
    override fun getValue() = element

    override fun navigate(requestFocus: Boolean) {
        if (element is NavigationItem) {
            element.navigate(requestFocus)
        }
    }

    override fun canNavigate() = element is NavigationItem && element.canNavigate()

    override fun canNavigateToSource() = element is NavigationItem && element.canNavigateToSource()

    override fun getAlphaSortKey() = (element as PsiNamedElement).name!!

    override fun getPresentation() = (element as NavigationItem).presentation!!

    override fun getChildren(): Array<TreeElement> {
        if (element is I18nFile) {
            val properties = PsiTreeUtil.getChildrenOfType(element, I18nProperty::class.java)
            if (properties != null) {
                val treeElements = ArrayList<TreeElement>(properties.size)
                properties.mapTo(treeElements) { I18nStructureViewElement(it) }
                return treeElements.toTypedArray()
            }
            return emptyArray()
        } else {
            return emptyArray()
        }
    }
}