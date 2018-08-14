import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IStarkeUserTypesMySuffix } from 'app/shared/model/starke-user-types-my-suffix.model';
import { StarkeUserTypesMySuffixService } from './starke-user-types-my-suffix.service';

@Component({
    selector: 'jhi-starke-user-types-my-suffix-delete-dialog',
    templateUrl: './starke-user-types-my-suffix-delete-dialog.component.html'
})
export class StarkeUserTypesMySuffixDeleteDialogComponent {
    starkeUserTypes: IStarkeUserTypesMySuffix;

    constructor(
        private starkeUserTypesService: StarkeUserTypesMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.starkeUserTypesService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'starkeUserTypesListModification',
                content: 'Deleted an starkeUserTypes'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-starke-user-types-my-suffix-delete-popup',
    template: ''
})
export class StarkeUserTypesMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ starkeUserTypes }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(StarkeUserTypesMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.starkeUserTypes = starkeUserTypes;
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
