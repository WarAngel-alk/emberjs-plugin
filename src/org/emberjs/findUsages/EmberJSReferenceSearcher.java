package org.emberjs.findUsages;

import com.intellij.lang.javascript.index.JSNamedElementProxy;
import com.intellij.openapi.application.QueryExecutorBase;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.util.Processor;
import org.emberjs.codeInsight.ComponentUtil;
import org.jetbrains.annotations.NotNull;

/**
 * @author Kristian Mandrup
 */
public class EmberJSReferenceSearcher extends QueryExecutorBase<PsiReference, ReferencesSearch.SearchParameters> {
    protected EmberJSReferenceSearcher() {
        super(true);
    }

    // TODO: How can we do something similar/meaningful for Ember??
    @Override
    public void processQuery(@NotNull ReferencesSearch.SearchParameters queryParameters, @NotNull final Processor<PsiReference> consumer) {
        final PsiElement element = queryParameters.getElementToSearch();
        final JSNamedElementProxy component = ComponentUtil.getComponent(element);
        if (component == null) return;

        queryParameters.getOptimizer().searchWord(component.getName(), queryParameters.getEffectiveSearchScope(), true, component);
    }
}
