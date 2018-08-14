import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { StarkeUserTypesMySuffix } from 'app/shared/model/starke-user-types-my-suffix.model';
import { StarkeUserTypesMySuffixService } from './starke-user-types-my-suffix.service';
import { StarkeUserTypesMySuffixComponent } from './starke-user-types-my-suffix.component';
import { StarkeUserTypesMySuffixDetailComponent } from './starke-user-types-my-suffix-detail.component';
import { StarkeUserTypesMySuffixUpdateComponent } from './starke-user-types-my-suffix-update.component';
import { StarkeUserTypesMySuffixDeletePopupComponent } from './starke-user-types-my-suffix-delete-dialog.component';
import { IStarkeUserTypesMySuffix } from 'app/shared/model/starke-user-types-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class StarkeUserTypesMySuffixResolve implements Resolve<IStarkeUserTypesMySuffix> {
    constructor(private service: StarkeUserTypesMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((starkeUserTypes: HttpResponse<StarkeUserTypesMySuffix>) => starkeUserTypes.body));
        }
        return of(new StarkeUserTypesMySuffix());
    }
}

export const starkeUserTypesRoute: Routes = [
    {
        path: 'starke-user-types-my-suffix',
        component: StarkeUserTypesMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StarkeUserTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'starke-user-types-my-suffix/:id/view',
        component: StarkeUserTypesMySuffixDetailComponent,
        resolve: {
            starkeUserTypes: StarkeUserTypesMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StarkeUserTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'starke-user-types-my-suffix/new',
        component: StarkeUserTypesMySuffixUpdateComponent,
        resolve: {
            starkeUserTypes: StarkeUserTypesMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StarkeUserTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'starke-user-types-my-suffix/:id/edit',
        component: StarkeUserTypesMySuffixUpdateComponent,
        resolve: {
            starkeUserTypes: StarkeUserTypesMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StarkeUserTypes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const starkeUserTypesPopupRoute: Routes = [
    {
        path: 'starke-user-types-my-suffix/:id/delete',
        component: StarkeUserTypesMySuffixDeletePopupComponent,
        resolve: {
            starkeUserTypes: StarkeUserTypesMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StarkeUserTypes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
