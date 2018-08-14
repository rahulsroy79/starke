import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { EntityReviewsMySuffix } from 'app/shared/model/entity-reviews-my-suffix.model';
import { EntityReviewsMySuffixService } from './entity-reviews-my-suffix.service';
import { EntityReviewsMySuffixComponent } from './entity-reviews-my-suffix.component';
import { EntityReviewsMySuffixDetailComponent } from './entity-reviews-my-suffix-detail.component';
import { EntityReviewsMySuffixUpdateComponent } from './entity-reviews-my-suffix-update.component';
import { EntityReviewsMySuffixDeletePopupComponent } from './entity-reviews-my-suffix-delete-dialog.component';
import { IEntityReviewsMySuffix } from 'app/shared/model/entity-reviews-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class EntityReviewsMySuffixResolve implements Resolve<IEntityReviewsMySuffix> {
    constructor(private service: EntityReviewsMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((entityReviews: HttpResponse<EntityReviewsMySuffix>) => entityReviews.body));
        }
        return of(new EntityReviewsMySuffix());
    }
}

export const entityReviewsRoute: Routes = [
    {
        path: 'entity-reviews-my-suffix',
        component: EntityReviewsMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EntityReviews'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'entity-reviews-my-suffix/:id/view',
        component: EntityReviewsMySuffixDetailComponent,
        resolve: {
            entityReviews: EntityReviewsMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EntityReviews'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'entity-reviews-my-suffix/new',
        component: EntityReviewsMySuffixUpdateComponent,
        resolve: {
            entityReviews: EntityReviewsMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EntityReviews'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'entity-reviews-my-suffix/:id/edit',
        component: EntityReviewsMySuffixUpdateComponent,
        resolve: {
            entityReviews: EntityReviewsMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EntityReviews'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const entityReviewsPopupRoute: Routes = [
    {
        path: 'entity-reviews-my-suffix/:id/delete',
        component: EntityReviewsMySuffixDeletePopupComponent,
        resolve: {
            entityReviews: EntityReviewsMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EntityReviews'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
