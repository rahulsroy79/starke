import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { EntityDetailsMySuffix } from 'app/shared/model/entity-details-my-suffix.model';
import { EntityDetailsMySuffixService } from './entity-details-my-suffix.service';
import { EntityDetailsMySuffixComponent } from './entity-details-my-suffix.component';
import { EntityDetailsMySuffixDetailComponent } from './entity-details-my-suffix-detail.component';
import { EntityDetailsMySuffixUpdateComponent } from './entity-details-my-suffix-update.component';
import { EntityDetailsMySuffixDeletePopupComponent } from './entity-details-my-suffix-delete-dialog.component';
import { IEntityDetailsMySuffix } from 'app/shared/model/entity-details-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class EntityDetailsMySuffixResolve implements Resolve<IEntityDetailsMySuffix> {
    constructor(private service: EntityDetailsMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((entityDetails: HttpResponse<EntityDetailsMySuffix>) => entityDetails.body));
        }
        return of(new EntityDetailsMySuffix());
    }
}

export const entityDetailsRoute: Routes = [
    {
        path: 'entity-details-my-suffix',
        component: EntityDetailsMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EntityDetails'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'entity-details-my-suffix/:id/view',
        component: EntityDetailsMySuffixDetailComponent,
        resolve: {
            entityDetails: EntityDetailsMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EntityDetails'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'entity-details-my-suffix/new',
        component: EntityDetailsMySuffixUpdateComponent,
        resolve: {
            entityDetails: EntityDetailsMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EntityDetails'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'entity-details-my-suffix/:id/edit',
        component: EntityDetailsMySuffixUpdateComponent,
        resolve: {
            entityDetails: EntityDetailsMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EntityDetails'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const entityDetailsPopupRoute: Routes = [
    {
        path: 'entity-details-my-suffix/:id/delete',
        component: EntityDetailsMySuffixDeletePopupComponent,
        resolve: {
            entityDetails: EntityDetailsMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EntityDetails'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
