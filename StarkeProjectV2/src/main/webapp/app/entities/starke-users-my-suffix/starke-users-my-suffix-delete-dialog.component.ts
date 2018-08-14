import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IStarkeUsersMySuffix } from 'app/shared/model/starke-users-my-suffix.model';
import { StarkeUsersMySuffixService } from './starke-users-my-suffix.service';

@Component({
    selector: 'jhi-starke-users-my-suffix-delete-dialog',
    templateUrl: './starke-users-my-suffix-delete-dialog.component.html'
})
export class StarkeUsersMySuffixDeleteDialogComponent {
    starkeUsers: IStarkeUsersMySuffix;

    constructor(
        private starkeUsersService: StarkeUsersMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.starkeUsersService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'starkeUsersListModification',
                content: 'Deleted an starkeUsers'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-starke-users-my-suffix-delete-popup',
    template: ''
})
export class StarkeUsersMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ starkeUsers }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(StarkeUsersMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.starkeUsers = starkeUsers;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
