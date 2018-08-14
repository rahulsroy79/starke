/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Starkev2TestModule } from '../../../test.module';
import { StarkeUserTypesMySuffixDeleteDialogComponent } from 'app/entities/starke-user-types-my-suffix/starke-user-types-my-suffix-delete-dialog.component';
import { StarkeUserTypesMySuffixService } from 'app/entities/starke-user-types-my-suffix/starke-user-types-my-suffix.service';

describe('Component Tests', () => {
    describe('StarkeUserTypesMySuffix Management Delete Component', () => {
        let comp: StarkeUserTypesMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<StarkeUserTypesMySuffixDeleteDialogComponent>;
        let service: StarkeUserTypesMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Starkev2TestModule],
                declarations: [StarkeUserTypesMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(StarkeUserTypesMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(StarkeUserTypesMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StarkeUserTypesMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
